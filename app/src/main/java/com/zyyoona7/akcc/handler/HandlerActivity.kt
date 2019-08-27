package com.zyyoona7.akcc.handler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.zyyoona7.akcc.R
import com.zyyoona7.akcc.animation.AnimationActivity
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityHandlerBinding
import java.lang.ref.WeakReference

const val MSG_NORMAL_1 = 1
const val TAG="HandlerActivity"

class HandlerActivity : BindingActivity<ActivityHandlerBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HandlerActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mainHandler: Handler

    override fun getLayoutId(): Int {
        return R.layout.activity_handler
    }

    override fun initVarAndViews(savedInstanceState: Bundle?) {
        mainHandler = Handler()
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initListeners(savedInstanceState: Bundle?) {
        binding.btnNormal1.setOnClickListener {
            doNormalHandlerTask()
        }
    }

    private fun handleMessage(msg: Message?) {
        when (msg?.what ?: -1) {
            MSG_NORMAL_1 -> {
                callNormal1()
            }
            else -> {
            }
        }
    }

    private fun doNormalHandlerTask() {
        Thread {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
//            val msg = Message.obtain()
//            msg.what = MSG_NORMAL_1
//            mainHandler.sendMessage(msg)
            mainHandler.post { callNormal1() }
        }.start()
    }

    private fun callNormal1() {
        Toast.makeText(this, "normal1 execute.", Toast.LENGTH_SHORT).show()
        Log.d(TAG,"current thread:${Thread.currentThread()}")
    }

    private class MainHandler constructor(activity: HandlerActivity) : Handler() {

        val reference: WeakReference<HandlerActivity?> = WeakReference(activity)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            reference.get()?.handleMessage(msg)
        }
    }
}
