package com.bhanu.nasaphotooftheday

import android.app.Application
import com.bhanu.nasaphotooftheday.di.networkModule
import com.bhanu.nasaphotooftheday.di.viewmodelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(
                viewmodelModules,
                networkModule
            )
        }
    }
}