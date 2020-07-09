package com.bobo.mytodolist.data

import com.bobo.mytodolist.async.*

// PASS

/**
 * 데이터 작업 처리를 위한 Repository module
 * Repository(물품저장소) = 민원접수창구(2차); 내부와 더 밀접해있다.
 * 또한 db와 연동되며 db 내 위치한 민원처리전담소(DAO)에 업무를 맡김
 */

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks() = taskDao.getAllTasks()

    fun getNotFinishedTasks() = taskDao.getNotFinishedTasks()

    fun getTask(taskId: Long) = taskDao.getTask(taskId)

    fun getLargestOrder() : Long {
        return GetOrderAsyncTask(taskDao).execute().get()
    }
    fun getFinishedTasks() = taskDao.getFinishedTasks()

    fun insert(task: Task) {
        InsertAsyncTask(taskDao).execute(task)
    }

    fun update(task: Task) {
        UpdateAsyncTask(taskDao).execute(task)
    }

    fun delete(task: Task) {
        DeleteAsyncTask(taskDao).execute(task)
    }

    fun emptyBin() {
        EmptyBinAsyncTask(taskDao).execute()
    }

    fun updateAllTasks(tasks: List<Task>) {
        UpdateAllTasksAsyncTask(taskDao).execute(tasks)
    }

    fun moveTask(task: Task) {
        task.isDeleted = !task.isDeleted
        UpdateAsyncTask(taskDao).execute(task)
    }

    fun setTagColor(task: Task, color: Int) {
        task.tag = color
        UpdateAsyncTask(taskDao).execute(task)
    }

    companion object {

        // For singleton instantiation
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: TaskDao) =
            instance ?: synchronized(this) {
                instance ?: TaskRepository(taskDao).also { instance = it }
            }
    }
}