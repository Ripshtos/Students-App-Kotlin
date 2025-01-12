package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.EditStudentActivityBinding
import com.example.studentapp.databinding.NewStudentActivityBinding
import com.example.studentapp.model.Model
import com.example.studentapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: EditStudentActivityBinding

    companion object {
        const val RESULT_DELETED = 2
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //add binding and inflate it
        binding = EditStudentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // change the title of the action bar
        supportActionBar?.title = "Edit Student"
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
                Model.shared.update(student) {
                    val resultIntent = Intent().apply {
                        putExtra("studentName", name)
                        putExtra("studentId", studentId)
                        putExtra("studentAddress", address)
                        putExtra("studentPhone", phoneNumber)
                        putExtra("isChecked", isChecked)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            } else {
                // Show error (e.g. Toast)
            }
        }

        binding.deleteBtn.setOnClickListener {
            if (studentId != null && studentId.isNotEmpty()) {
                Model.shared.getStudent(studentId) {
                    Model.shared.delete(it) {
                        setResult(RESULT_DELETED)
                        finish()
                    }
                }
            }
        }

        // add a click listener to the cancel button
        binding.cancelBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
