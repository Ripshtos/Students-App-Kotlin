package com.example.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.StudentDetailsActivityBinding

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
        val isChecked = intent.getBooleanExtra("isChecked", false)

        // Populate the fields with the received data
        binding.studentName.setText(name)
        binding.studentId.setText(studentId)
        binding.studentAddress.setText(address)
        binding.studentPhone.setText(phoneNumber)
        binding.isChecked.isChecked = isChecked


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
