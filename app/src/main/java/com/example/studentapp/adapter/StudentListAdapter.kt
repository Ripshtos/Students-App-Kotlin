package com.example.studentapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Model
import com.example.studentapp.model.Student

class StudentListAdapter(
    private val data: MutableList<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.studentRowImage.setImageResource(R.drawable.student_avatar)
            binding.studentRowName.text = student.name
            binding.studentRowId.text = student.id
            binding.studentRowCheckBox.isChecked = student.isChecked

            binding.studentRowCheckBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                Model.shared.update(student){}
            }

            binding.root.setOnClickListener {
                onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<Student>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}
