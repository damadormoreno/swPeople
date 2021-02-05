package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.usecases.PeopleRepository
import org.koin.dsl.

val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }

}
