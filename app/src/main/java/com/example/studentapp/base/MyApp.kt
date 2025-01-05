package com.example.studentapp.base

import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

// Accessing the application context:
val appContext = MyApp.instance.applicationContext