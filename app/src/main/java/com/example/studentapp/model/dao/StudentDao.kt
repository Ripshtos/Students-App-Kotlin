package com.example.studentapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentapp.model.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudent(): List<Student>

    @Query("SELECT * FROM Student WHERE id =:id")
    fun getStudent(id: String): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg student: Student)

    @Delete
    fun delete(student: Student)
}