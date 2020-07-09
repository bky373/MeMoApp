/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobo.mytodolist.utilities

import android.content.Context
import com.bobo.mytodolist.data.AppDatabase
import com.bobo.mytodolist.viewModels.TaskDetailViewModelFactory
import com.bobo.mytodolist.data.TaskRepository
import com.bobo.mytodolist.viewModels.BinViewModelFactory
import com.bobo.mytodolist.viewModels.TaskViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {
    // context는 결국 database와의 적절한 연동을 위해 필요?
    private fun getTaskRepository(context: Context): TaskRepository {
        return TaskRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).taskDao()
        )
    }

    fun provideTaskViewModelFactory(
        context: Context
    ): TaskViewModelFactory {
        val repository = getTaskRepository(context)
        return TaskViewModelFactory(repository)
    }

    fun provideTaskDetailViewModelFactory(
        context: Context,
        taskId: Long
    ): TaskDetailViewModelFactory {
        return TaskDetailViewModelFactory(
            getTaskRepository(context),
            taskId
        )
    }

    fun provideBinViewModelFactory(
        context: Context
    ): BinViewModelFactory {
        val repository = getTaskRepository(context)
        return BinViewModelFactory(repository)
    }

}
