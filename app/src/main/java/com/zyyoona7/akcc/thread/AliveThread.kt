package com.zyyoona7.akcc.thread

import android.util.Log

class AliveThread : Thread() {

    private var sleepRunnable: (() -> Unit)? = null
    private var endRunnable: (() -> Unit)? = null

    companion object {
        const val TAG = "AliveThread"
    }

    override fun run() {
        super.run()
        Log.d(TAG, "thread alive:${isAlive}")
        Log.d(TAG, "thread run...")
        try {
            sleepRunnable?.invoke()
            sleep(2000)
        } catch (e: InterruptedException) {
            Log.d(TAG, "thread has been interrupted..")
        }
        endRunnable?.invoke()
    }

    fun sleepRunnable(runnable: () -> Unit) {
        this.sleepRunnable = runnable
    }

    fun endRunnable(runnable: () -> Unit) {
        endRunnable = runnable
    }
}