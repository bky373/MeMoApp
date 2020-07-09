package com.bobo.mytodolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bobo.mytodolist.data.TaskRepository

class TaskDetailViewModelFactory(
    private val taskRepository: TaskRepository,
    private val taskId: Long
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskDetailViewModel(taskRepository, taskId) as T
    }
}
