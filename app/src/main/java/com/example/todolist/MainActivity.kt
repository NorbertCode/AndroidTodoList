package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecycleView adapter
        val adapter = TodoListAdapter(applicationContext)
        binding.rvTodoList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvTodoList.adapter = adapter

        GlobalScope.launch {
            val tasksToLoad = TasksDatabase.getInstance(applicationContext)!!.TasksDao().getAllTasks()
            adapter.loadTasks(tasksToLoad)
        }

        binding.btAddTask.setOnClickListener {
            adapter.addTask(binding.etTodoInput.text.toString())
            binding.etTodoInput.text.clear()
        }

        binding.btFinishTask.setOnClickListener {
            adapter.finishTasks()
        }
    }
}