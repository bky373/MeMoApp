package com.bobo.mytodolist.async

import android.os.AsyncTask
import com.bobo.mytodolist.data.Task
import com.bobo.mytodolist.data.TaskDao

class GetOrderAsyncTask(val taskDao: TaskDao) : AsyncTask<Task, Unit, Long>() {
    override fun doInBackground(vararg params: Task?): Long {
        return taskDao.getLargestOrder()
    }
}