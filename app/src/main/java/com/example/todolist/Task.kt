package com.example.todolist

import android.widget.CheckBox

data class Task(
    val name: String
) {
    lateinit var checkbox: CheckBox
}

