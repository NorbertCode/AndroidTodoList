package com.example.todolist

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.databinding.TodoItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoListAdapter(private val applicationContext: Context) : RecyclerView.Adapter<TodoListAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(binding: TodoItemBinding) : ViewHolder(binding.root) {
        val taskName = binding.tvTodoTitle
        val checkbox = binding.cbDone
    }

    private var tasks: MutableList<Task> = mutableListOf()

    fun loadTasks(savedTasks: MutableList<Task>) {
        tasks = savedTasks
    }

    fun addTask(name: String) {
        val newTask = Task(name)
        GlobalScope.launch {
            TasksDatabase.getInstance(applicationContext)!!.TasksDao().insert(newTask)
        }
        tasks.add(newTask)
        notifyItemInserted(tasks.size - 1)
    }

    fun finishTasks() {
        for (i in tasks.size - 1 downTo 0) {
            if (tasks[i].isDone) {
                GlobalScope.launch {
                    TasksDatabase.getInstance(applicationContext)!!.TasksDao().delete(tasks[i])
                }
                tasks.removeAt(i)
            }
        }
        // notifying at each task removal caused out-of-range errors when you finished some tasks and check any later one
        // that was probably because it wasn't updated properly and the position in onBindViewHolder() stayed the same
        // using this method instead fixed these errors
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks[position].name
        holder.checkbox.isChecked = tasks[position].isDone
        holder.checkbox.setOnClickListener { tasks[position].isDone = holder.checkbox.isChecked }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}