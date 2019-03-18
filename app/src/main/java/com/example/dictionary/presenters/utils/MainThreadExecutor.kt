package com.example.dictionary.presenters.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

object MainThreadExecutor : Executor {

    private val mHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}
