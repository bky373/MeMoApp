package com.bobo.mytodolist.async

import android.os.AsyncTask
import com.bobo.mytodolist.data.TaskDao

class EmptyBinAsyncTask(val taskDao: TaskDao) : AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit) {
        taskDao.emptyBin()
    }
}