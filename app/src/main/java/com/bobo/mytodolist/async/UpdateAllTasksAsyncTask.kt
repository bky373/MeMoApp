package com.bobo.mytodolist.async

import android.os.AsyncTask
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskDao

class UpdateAllTasksAsyncTask(val taskDao: TaskDao) : AsyncTask<List<Task>, Unit, Unit>() {
    override fun doInBackground(vararg params: List<Task>?) {
        if(params[0]?.size != 0){
            params[0]?.let { taskDao.updateAllTasks(it) }
        }
    }
}