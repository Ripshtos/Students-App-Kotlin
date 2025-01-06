package com.example.studentapp.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentapp.base.MyApp
import com.example.studentapp.model.Student

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object DB {

    // Use lazy so that the DB is built once, on first usage
    val database: AppLocalDbRepository by lazy {

        val context = MyApp.instance.applicationContext

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
