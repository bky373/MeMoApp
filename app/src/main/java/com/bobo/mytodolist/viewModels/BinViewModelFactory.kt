package com.bobo.mytodolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bobo.mytodolist.data.TaskRepository

class BinViewModelFactory(
    private val repository: TaskRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BinViewModel(repository) as T
    }
}
