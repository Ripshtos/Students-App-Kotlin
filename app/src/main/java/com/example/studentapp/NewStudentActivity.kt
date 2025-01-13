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

        //add binding and inflate it
        binding = NewStudentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // change the title of the action bar
        supportActionBar?.title = "Add New Student"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // add a click listener to the save button
        binding.saveBtn.setOnClickListener {
            val name = binding.studentName.text.toString()
            val studentId = binding.studentId.text.toString()
            val address = binding.studentAddress.text.toString()
            val phoneNumber = binding.studentPhone.text.toString()
            val isChecked = binding.isChecked.isChecked

            if (name.isNotEmpty() && studentId.isNotEmpty()) {
                val student = Student(studentId, name, "@/drawable/student_avatar",
                    isChecked, phoneNumber, address)
                Model.shared.add(student) {
                    // callback on main thread
                    finish()
                }
            } else {
                // Show error (e.g. Toast)
            }
        }

        // add a click listener to the cancel button
        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
