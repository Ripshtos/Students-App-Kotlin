package com.example.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.NewStudentActivityBinding
import com.example.studentapp.model.Model
import com.example.studentapp.model.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var binding: NewStudentActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewStudentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add New Student"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.saveBtn.setOnClickListener {
            val name = binding.studentName.text.toString()
            val studentId = binding.studentId.text.toString()

            if (name.isNotEmpty() && studentId.isNotEmpty()) {
                val student = Student(name, studentId, isChecked = false , avatarUrl = "@/drawable/student_avatar")
                Model.shared.add(student) {
                    // callback on main thread
                    finish()
                }
            } else {
                // Show error (e.g. Toast)
            }
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
