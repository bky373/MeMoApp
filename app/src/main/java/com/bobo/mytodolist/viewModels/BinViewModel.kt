package com.bobo.mytodolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskRepository
import kotlinx.coroutines.launch

class BinViewModel (
    private val taskRepository: TaskRepository
) : ViewModel() {

    val binItems = taskRepository.getFinishedTasks()

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }

    fun emptyBin() {
        viewModelScope.launch {
            taskRepository.emptyBin()
        }
    }
}