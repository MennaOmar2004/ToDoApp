package com.example.todoapp.room

import kotlinx.coroutines.flow.Flow


class TasksRepo(private val tasksDao: TasksDao){

    val allTAsks:Flow<List<TasksEntity>> = tasksDao.getAllTasks() // دى بدل ال query
    val completedTasks : Flow<List<TasksEntity>> = tasksDao.getCompletedTask()
    val notCompletedTasks : Flow<List<TasksEntity>> = tasksDao.getNotCompletedTask()

    suspend fun insert(tasksEntity: TasksEntity){
        tasksDao.insertTask(tasksEntity)
    }

    suspend fun delete(tasksEntity: TasksEntity){
        tasksDao.deleteTask(tasksEntity)
    }

//    suspend fun query():Flow<List<TasksEntity>> {
//        return tasksDao.getAllTasks()
//    }

    suspend fun update(tasksEntity: TasksEntity){
        tasksDao.update(tasksEntity)
    }


}