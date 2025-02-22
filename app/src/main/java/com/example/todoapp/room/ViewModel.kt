package com.example.todoapp.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

    val allTasks:Flow<List<TasksEntity>>
    val completedTasks:Flow<List<TasksEntity>>
    val notCompletedTasks:Flow<List<TasksEntity>>
    private val repository: TasksRepo

    init {
        val dao = TasksDataBase.getDataBase(application).tasksDao()
        repository = TasksRepo(dao)
        allTasks = repository.allTAsks
        completedTasks = repository.completedTasks
        notCompletedTasks = repository.notCompletedTasks

    }

    fun insertTask(tasksEntity: TasksEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tasksEntity)
//            Log.d( "insertTask: ","taskadd:$tasksEntity")

        }
    }

    fun deleteTask(tasksEntity: TasksEntity) {
        viewModelScope.launch(Dispatchers.IO)
        { repository.delete(tasksEntity) }
    }

    fun updateTask(tasksEntity: TasksEntity){
        viewModelScope.launch (Dispatchers.IO)
        {
            repository.update(tasksEntity)
        }
    }



}