package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun TasksDao() : TasksDao

    companion object {
        private var instance: TasksDatabase? = null

        fun getInstance(context: Context): TasksDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, TasksDatabase::class.java, "tasks").build()
            }
            return instance
        }

        fun deleteInstance() {
            instance = null
        }
    }
}
