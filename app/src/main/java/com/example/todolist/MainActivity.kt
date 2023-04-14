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

        val adapter = TodoListAdapter(addTasks())

        binding.rvTodoList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvTodoList.adapter = adapter
    }

    private fun addTasks(): List<Task> = buildList {
        for (i in 0 .. 10) {
            val newTask = Task("$i")
            add(newTask)
        }
    }
}