package com.davidups.starwars.features.people.data.local

import androidx.lifecycle.LiveData
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PeopleDB {
    fun getPersons(): List<PersonEntity>
    fun addPerson(person: PersonEntity): Any
    fun addPersons(persons: List<PersonEntity>)
    fun getFavoritePersons(): LiveData<List<PersonEntity>>
    fun setFavorite(person: PersonEntity): Any
}