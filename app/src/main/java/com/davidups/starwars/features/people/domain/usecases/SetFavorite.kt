package com.davidups.starwars.features.people.domain.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import com.davidups.starwars.features.people.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow

class SetFavorite(private val repository: PeopleRepository): UseCase<Any, SetFavorite.Params>() {

    data class Params(val personEntity: PersonEntity)

    override fun run(params: Params?): Flow<Any> {
        return repository.setFavorite(params!!.personEntity)
    }
}