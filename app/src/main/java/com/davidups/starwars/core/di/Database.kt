package com.davidups.starwars.core.di

import androidx.room.Room
import com.davidups.starwars.core.database.AppDatabase
import com.davidups.starwars.features.people.data.local.PeopleLocal
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "SWMovieDB"
        )
            .build()
    }

    factory { PeopleLocal(get()) }
}
