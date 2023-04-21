package com.example.todolist

import androidx.room.*

@Dao
interface TasksDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): MutableList<Task>
}
