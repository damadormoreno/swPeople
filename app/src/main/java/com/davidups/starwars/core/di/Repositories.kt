package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.domain.repositories.PeopleRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get(), get(), get(), get()) }

}
