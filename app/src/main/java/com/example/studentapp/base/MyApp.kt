package com.example.studentapp.base

import android.app.Application

class MyApp : Application() {

    companion object {
        @Volatile
        private var _instance: MyApp? = null

        val instance: MyApp
            get() = _instance ?: throw IllegalStateException(
                "Application not created yet or has been terminated."
            )
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}
