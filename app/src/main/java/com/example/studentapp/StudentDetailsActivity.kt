package com.example.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.StudentDetailsActivityBinding
import com.example.studentapp.model.Model
import com.example.studentapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: StudentDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //add binding and inflate it
        binding = StudentDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // change the title of the action bar
        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")
        val address = intent.getStringExtra("studentAddress")
        val phoneNumber = intent.getStringExtra("studentPhone")

        // Populate the fields with the received data
        binding.studentName.setText(name)
        binding.studentId.setText(studentId)
        binding.studentAddress.setText(address)
        binding.studentPhone.setText(phoneNumber)


        // add a click listener to the edit button
        binding.editBtn.setOnClickListener {
            // TODO: Implement the edit functionality
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
