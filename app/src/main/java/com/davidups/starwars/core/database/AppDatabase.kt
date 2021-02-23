package com.davidups.starwars.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidups.starwars.core.dao.PersonDAO
import com.davidups.starwars.features.people.data.models.entity.PersonEntity


@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personEntityDao(): PersonDAO

}

