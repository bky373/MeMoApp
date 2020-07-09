package com.bobo.mytodolist.async

import android.os.AsyncTask
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskDao

class DeleteAsyncTask(val taskDao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
    override fun doInBackground(vararg params: Task?) {
        params[0]?.let { taskDao.delete(it) }
    }
}