package com.example.todoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TasksEntity::class], version = 1, exportSchema = false)
abstract class TasksDataBase:RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var INSTANCE: TasksDataBase? = null
        fun getDataBase(context: Context): TasksDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasksDataBase::class.java,
                    "Tasks_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}