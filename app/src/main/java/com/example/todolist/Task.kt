package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var name: String,
    var isDone: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)