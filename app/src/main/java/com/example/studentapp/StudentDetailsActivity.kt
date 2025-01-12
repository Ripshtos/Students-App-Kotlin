package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.StudentDetailsActivityBinding

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: StudentDetailsActivityBinding

    private val editStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                result.data?.let {
                    val updatedName = it.getStringExtra("studentName")
                    val updatedId = it.getStringExtra("studentId")
                    val updatedAddress = it.getStringExtra("studentAddress")
                    val updatedPhone = it.getStringExtra("studentPhone")
                    val updatedIsChecked = it.getBooleanExtra("isChecked", false)

                    // Update the UI with the new data
                    binding.studentName.setText(updatedName)
                    binding.studentId.setText(updatedId)
                    binding.studentAddress.setText(updatedAddress)
                    binding.studentPhone.setText(updatedPhone)
                    binding.isChecked.isChecked = updatedIsChecked
                }
            }
            EditStudentActivity.RESULT_DELETED -> finish()
        }

    }

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
            editStudentLauncher.launch(Intent(this, EditStudentActivity::class.java).apply {
                putExtra("studentName", name)
                putExtra("studentId", studentId)
                putExtra("studentAddress", address)
                putExtra("studentPhone", phoneNumber)
                putExtra("isChecked", isChecked)
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
