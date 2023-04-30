package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityTaskOptionsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskOptions : AppCompatActivity() {
    private lateinit var binding: ActivityTaskOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("TASK_NAME")) {
            binding.etName.setText(intent.getStringExtra("TASK_NAME"))
        }

        binding.btDiscard.setOnClickListener { exit() }
        binding.btSave.setOnClickListener { save() }
    }

    private fun exit() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    private fun save() {
        if (intent.hasExtra("TASK_ID")) {
            val updatedTask = Task(binding.etName.text.toString(), false, intent.getIntExtra("TASK_ID", -1))
            GlobalScope.launch {
                TasksDatabase.getInstance(applicationContext)!!.TasksDao().update(updatedTask)
            }
        }
        exit()
    }
}