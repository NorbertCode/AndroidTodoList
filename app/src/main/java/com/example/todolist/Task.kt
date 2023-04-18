package com.example.todolist

import androidx.lifecycle.ViewModel

data class Task(
    val name: String,
    var isDone: Boolean = false
)

data class SavedTasks(
    val tasks: MutableList<Task> = mutableListOf()
) : ViewModel()