package com.bobo.mytodolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskRepository
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val taskRepository: TaskRepository,
    val taskId: Long
) : ViewModel() {

    val task = taskRepository.getTask(taskId)

    fun editTask(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }

    fun setTagColor(task: Task, color: Int) {
        viewModelScope.launch {
            taskRepository.setTagColor(task, color)
        }
    }
}