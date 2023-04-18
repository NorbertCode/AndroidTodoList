package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tasksViewModel by viewModels<SavedTasks>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecycleView adapter
        val adapter = TodoListAdapter()
        binding.rvTodoList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvTodoList.adapter = adapter

        // Makes sure tasks don't get reset when rotating the phone
        // Doesn't save the data to the drive
        adapter.loadTasks(tasksViewModel.tasks)

        binding.btAddTask.setOnClickListener {
            adapter.addTask(binding.etTodoInput.text.toString())
            binding.etTodoInput.text.clear()
        }

        binding.btFinishTask.setOnClickListener {
            adapter.finishTasks()
        }
    }
}