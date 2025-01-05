package com.example.studentapp.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentapp.base.MyApp
import com.example.studentapp.model.Student

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDbRepository: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object DB {

    val database: AppLocalDbRepository by lazy {
        // Access the application context using MyApplication.instance
        val context = MyApp.instance.applicationContext

        Room.databaseBuilder(
            context = context,
            klass = AppLocalDbRepository::class.java,
            name = "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}