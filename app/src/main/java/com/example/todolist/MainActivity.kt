package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecycleView adapter
        val adapter = TodoListAdapter()
        binding.rvTodoList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvTodoList.adapter = adapter

        binding.btAddTask.setOnClickListener {
            adapter.addTask(binding.etTodoInput.text.toString())
            binding.etTodoInput.text.clear()
        }

        binding.btFinishTask.setOnClickListener {
            adapter.finishTasks()
        }
    }
}