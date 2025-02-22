package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun insertTask(tasksEntity: TasksEntity)

    @Delete
    suspend fun deleteTask(tasksEntity: TasksEntity)

    @Query("SELECT * FROM Tasks ORDER BY id ASC")
     fun getAllTasks():Flow<List<TasksEntity>>

    @Query("SELECT * FROM Tasks Where isComplete = 0 ")
    fun getNotCompletedTask():Flow<List<TasksEntity>>

    @Query("SELECT * FROM Tasks Where isComplete = 1 ")
     fun getCompletedTask():Flow<List<TasksEntity>>

    @Update
    suspend fun update(tasksEntity: TasksEntity)

}