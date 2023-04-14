package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.databinding.TodoItemBinding

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(binding: TodoItemBinding) : ViewHolder(binding.root) {
        val taskName = binding.tvTodoTitle
        val checkbox = binding.cbDone
    }

    private val tasks: MutableList<Task> = mutableListOf()

    fun addTask(name: String) {
        val newTask = Task(name)
        tasks.add(newTask)
    }

    fun finishTasks() {
        for (i in tasks.size - 1 downTo 0) {
            if (tasks[i].isDone)
                tasks.removeAt(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks[position].name
        holder.checkbox.setOnClickListener { tasks[position].isDone = holder.checkbox.isChecked }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}