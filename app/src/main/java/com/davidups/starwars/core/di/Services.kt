package com.davidups.starwars.core.di

import com.davidups.starwars.features.movies.services.MoviesService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { MoviesService(get()) }

}
