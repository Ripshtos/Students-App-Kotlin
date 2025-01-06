package com.example.studentapp.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentapp.base.EmptyCallback
import com.example.studentapp.base.StudentsCallback
import com.example.studentapp.base.StudentCallback
import com.example.studentapp.model.dao.DB
import com.example.studentapp.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors

class Model private constructor() {

    private val db: AppLocalDbRepository = DB.database
    private val executor = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = db.studentDao().getAllStudent()

            Thread.sleep(4000)

            mainHandler.post {
                callback(students)
            }
        }
    }

    fun getStudent(index: String, callback: StudentCallback) {
        executor.execute {
            val student = db.studentDao().getStudent(index)

            Thread.sleep(4000)

            mainHandler.post {
                callback(student)
            }
        }
    }

    fun add(student: Student, callback: EmptyCallback) {
        executor.execute {
            db.studentDao().insertAll(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        executor.execute {
            db.studentDao().delete(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }
}