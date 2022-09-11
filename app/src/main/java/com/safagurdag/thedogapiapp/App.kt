package com.safagurdag.thedogapiapp

import android.app.Application
import com.safagurdag.framework.resource.TimberReleaseTree
import dagger.hilt.android.HiltAndroidApp
import safagurdag.thedogapp.BuildConfig
import timber.log.Timber


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }


    private fun setupTimber() {

        Timber.plant(
            if (BuildConfig.DEBUG) Timber.DebugTree()
            else TimberReleaseTree()
        )
    }
}