package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.databinding.StudentListActivityBinding
import com.example.studentapp.adapter.StudentListAdapter
import com.example.studentapp.model.Model

class StudentsListActivity : AppCompatActivity() {

    private lateinit var binding: StudentListActivityBinding
    private lateinit var adapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StudentListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Students List"

        // Setup RecyclerView
        adapter = StudentListAdapter(mutableListOf()) {}


        binding.studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentsRecyclerView.adapter = adapter

        // Load data from DB
        loadStudents()

        // Add button
        binding.addStudentBtn.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload data in case something changed
        loadStudents()
    }

    private fun loadStudents() {
        Model.shared.getAllStudents { list ->
            adapter.setData(list)
        }
    }
}
