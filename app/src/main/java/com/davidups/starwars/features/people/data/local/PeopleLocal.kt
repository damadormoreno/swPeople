package com.davidups.starwars.features.people.data.local

import androidx.lifecycle.LiveData
import com.davidups.starwars.core.database.AppDatabase
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

class PeopleLocal(appDatabase: AppDatabase): PeopleDB {

    private val personDao by lazy {
        appDatabase.personEntityDao()
    }

    override fun getPersons(): List<PersonEntity> = personDao.getAllPerson()

    override fun addPerson(person: PersonEntity): Any = personDao.insert(person)

    override fun addPersons(persons: List<PersonEntity>) = personDao.insertAll(persons)

    override fun getFavoritePersons(): LiveData<List<PersonEntity>> = personDao.getAllFavoritePerson()
    override fun setFavorite(person: PersonEntity): Any = personDao.setFavorite(person)


}