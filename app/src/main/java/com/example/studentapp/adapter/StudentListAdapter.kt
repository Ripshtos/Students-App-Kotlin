package com.example.studentapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Student

class StudentListAdapter(
    private val data: MutableList<Student>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.studentImageView.setImageResource(R.drawable.student_avatar)
            binding.nameTv.text = student.name
            binding.idTv.text = student.id
            binding.checkedCb.isChecked = student.isChecked

            binding.checkedCb.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                // If you want to persist the "isChecked" in DB immediately,
                // you can call a Model update here, or wait until some other action.
            }

            binding.root.setOnClickListener {
                onItemClick(student.id)
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
