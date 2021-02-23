package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.domain.usecases.GetNextPage
import com.davidups.starwars.features.people.domain.usecases.GetPeople
import com.davidups.starwars.features.people.domain.usecases.SetFavorite
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPeople(get())
    }
    factory {
        SetFavorite(get())
    }

    factory {
        GetNextPage(get())
    }
}
