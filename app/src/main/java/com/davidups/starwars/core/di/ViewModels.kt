package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PeopleViewModel(get(), get(), get()) }

}
