package com.zyyoona7.akcc.thread

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.zyyoona7.akcc.R
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityThreadBinding
import java.util.concurrent.*

class ThreadActivity : BindingActivity<ActivityThreadBinding>() {

    private val threadPool: ThreadPoolExecutor by lazy {
        ThreadPoolExecutor(
            2, 5,
            1000L, TimeUnit.MILLISECONDS,
            ArrayBlockingQueue(7)
        )
    }

    companion object {
        const val TAG = "ThreadActivity"
        fun start(context: Context) {
            val intent = Intent(context, ThreadActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_thread
    }

    override fun initVarAndViews(savedInstanceState: Bundle?) {
        threadAlive()
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initListeners(savedInstanceState: Bundle?) {
        binding.btnCore.setOnClickListener {
            testThreadPoolCore()
        }

        binding.btnMax.setOnClickListener {
            testThreadPoolMax()
        }

        binding.btnReuse.setOnClickListener {
            testThreadReuse()
        }
    }

    private fun threadAlive() {
        val aliveThread = AliveThread()
        aliveThread.sleepRunnable {
            postDelayed(100) {
                Log.d(TAG, "alive thread sleeping is alive:${aliveThread.isAlive}")
            }
        }

        aliveThread.endRunnable {
            postDelayed(100) {
                Log.d(TAG, "alive thread end is alive:${aliveThread.isAlive}")
//                aliveThread.start()
            }
        }
        postDelayed(1000) {
            aliveThread.start()
        }
    }

    private fun testThreadPoolCore() {
//        for (i in 0 until 1) {
            executeInThreadPool(0)
//        }
        postDelayed(2000){
            executeInThreadPool(1)
        }
        postDelayed(4000){
            executeInThreadPool(2)
        }
//        postDelayed(6000){
//            executeInThreadPool(3)
//        }
        postDelayed(5000){
            Log.d(TAG,"thread core thread active count${threadPool.activeCount}")
        }
    }

    private fun testThreadPoolMax() {
        for (i in 0 until 100) {
            executeInThreadPool(i)
        }
    }

    private fun testThreadReuse() {
        for (i in 0 until 5) {
            executeInThreadPool(i)
        }
        //ThreadPool keepAliveTime 为1s
        postDelayed(1300) {
            for (i in 0 until 5) {
                executeInThreadPool(i)
            }
        }
    }

    private fun executeInThreadPool(number: Int) {
        threadPool.execute {
            Log.d(TAG, "thread pool task $number currentThread:${Thread.currentThread().id}")
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                Log.d(TAG, "thread pool task interrupted.")
            }
        }
    }
}
