package com.davidups.starwars

import android.app.Application
import com.davidups.starwars.core.di.applicationModule
import com.davidups.starwars.core.di.databaseModule
import com.davidups.starwars.core.di.networkModule
import com.davidups.starwars.core.di.dataSourceModule
import com.davidups.starwars.core.di.repositoryModule
import com.davidups.starwars.core.di.useCaseModule
import com.davidups.starwars.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                applicationModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataSourceModule,
                databaseModule
            ))
        }
    }
}
