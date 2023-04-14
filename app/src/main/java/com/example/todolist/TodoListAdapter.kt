package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.databinding.TodoItemBinding

class TodoListAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TodoListAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(binding: TodoItemBinding) : ViewHolder(binding.root) {
        val taskName = binding.tvTodoTitle
        val checkbox = binding.cbDone
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks[position].name
        holder.checkbox.isChecked = false
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}