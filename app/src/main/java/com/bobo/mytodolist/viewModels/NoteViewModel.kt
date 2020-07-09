package com.bobo.mytodolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 뷰가 터치스크린이라면
// 뷰모델은 뷰에 연결된 민원접수창구(1차)

// GOT IT
class NoteViewModel internal constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val noteTasks = taskRepository.getNotFinishedTasks()

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insert(task)
        }
    }

    fun getLargestOrder() : Long {
        return runBlocking(viewModelScope.coroutineContext) {
            return@runBlocking taskRepository.getLargestOrder()
        }
    }

    fun moveTask(task: Task) {
        viewModelScope.launch {
            taskRepository.moveTask(task)
        }
    }

    fun updateAllTasks(tasks: List<Task>) {
        viewModelScope.launch {
            taskRepository.updateAllTasks(tasks)
        }
    }

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
