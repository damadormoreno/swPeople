package com.davidups.starwars.features.people.domain.repositories

import android.content.SharedPreferences
import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.extensions.SharedPrefences.set
import com.davidups.starwars.core.functional.Either
import com.davidups.starwars.core.functional.request
import com.davidups.starwars.core.platform.NetworkHandler
import com.davidups.starwars.core.platform.ServiceKOs
import com.davidups.starwars.features.people.data.local.PeopleLocal
import com.davidups.starwars.features.people.data.models.data.People
import com.davidups.starwars.features.people.data.models.data.Person
import com.davidups.starwars.features.people.data.models.entity.PeopleEntity
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import com.davidups.starwars.features.people.data.services.PeopleService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

interface PeopleRepository {

    fun getPeople(): Flow<Either<Failure, List<Person>>>
    fun getMorePeople(): Flow<Either<Failure, List<Person>>>
    fun setFavorite(personEntity: PersonEntity): Flow<Any>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PeopleService,
        private val networkHandler: NetworkHandler,
        private val shared: SharedPreferences,
        private val peopleDB: PeopleLocal

        ) : PeopleRepository {

        override fun getPeople(): Flow<Either<Failure, List<Person>>> {
            return flow {
                val people = peopleDB.getPersons()
                val time = shared.getLong("time", 0)
                if (people.isNullOrEmpty()
                    || time == 0L
                    || isFetchCurrentNeeded(shared.getLong("time", 0L))
                        ) {
                    emit(getPeopleApi())
                } else {
                    emit(getPersonsDB())
                }
            }.catch {
                emit(Either.Left(Failure.CustomError(it.stackTrace.hashCode(), it.localizedMessage)))
            }.flowOn(ioDispatcher)
        }

        override fun getMorePeople(): Flow<Either<Failure, List<Person>>> {
            return flow {
                val page = shared.getInt("page", -1)
                if ( page != -1) {
                    emit(getNextPage(page))
                }else {
                    emit(Either.Left(Failure.NoMorePages()))
                }
            }.flowOn(ioDispatcher)
        }

        override fun setFavorite(personEntity: PersonEntity): Flow<Any> {
           return flow {
               emit(peopleDB.setFavorite(personEntity))
           }.flowOn(ioDispatcher)
        }

        private fun getPeopleApi() : Either<Failure, List<Person>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getPeople(), { peopleEntity ->
                    saveInDb(peopleEntity)
                    peopleEntity.results?.toList()?.map { it.toPerson() } ?: listOf()
                }, PeopleEntity.empty())
                false -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun getNextPage(page: Int): Either<Failure, List<Person>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getPeopleByPage(page), { peopleEntity ->
                    saveInDb(peopleEntity)
                    peopleEntity.results?.toList()?.map { it.toPerson() } ?: listOf()
                    //peopleDB.getPersons().map { it.toPerson() }
                }, PeopleEntity.empty())
                false -> Either.Left(Failure.NetworkConnection())
            }
        }


        private fun getPersonsDB(): Either<Failure, List<Person>> {
            return try {
                Either.Right(peopleDB.getPersons().map { it.toPerson() })
            }catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        private fun saveInDb(apiResult: PeopleEntity) {

            shared["time"] = Date().time
            if (apiResult.next == null) {
                shared["page"] = -1
            } else {
                shared["page"] = apiResult.next.split("page=").last().toInt()
            }

            val persons = apiResult.results?: mutableListOf()

            peopleDB.addPersons(persons)
        }

        private fun isFetchCurrentNeeded(lastFetchTime: Long): Boolean {
            val oneMinuteInMillis = 60000
            val oneDayAgo = Date(lastFetchTime - (1440 * oneMinuteInMillis)).time
            return Date(lastFetchTime).before(Date(oneDayAgo))
        }
    }
}
