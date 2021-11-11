package com.ubaya.a160419009_todoapp.Util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TodoWorker(val context: Context, val params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(inputData.getString("TITLE").toString(),
            inputData.getString("MESSAGE").toString())
        return Result.success()
    }

}