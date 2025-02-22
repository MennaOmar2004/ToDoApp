package com.example.todoapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Tasks")
data class TasksEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val date:String,
    val isComplete:Boolean
)

