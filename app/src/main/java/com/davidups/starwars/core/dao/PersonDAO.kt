package com.davidups.starwars.core.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.davidups.starwars.core.platform.BaseDao
import com.davidups.starwars.features.people.data.models.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDAO: BaseDao<PersonEntity> {

    @Query("SELECT * FROM PersonEntity WHERE name == :name")
    fun getPersonByName(name: String): List<PersonEntity>

    @Query("SELECT * FROM PersonEntity")
    fun getAllPerson(): List<PersonEntity>

    @Query("SELECT * FROM PersonEntity WHERE isFavorite = 1")
    fun getAllFavoritePerson(): LiveData<List<PersonEntity>>

    @Query("DELETE FROM PersonEntity")
    fun deleteAllPerson()

    @Update(entity = PersonEntity::class)
    fun setFavorite(personEntity: PersonEntity)

}
