package com.zyyoona7.akcc.handler

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

private const val TAG="LooperThread"

class LooperThread : Thread() {
    var handler: Handler?=null
    private var handleMessageCount:Int=0

    override fun run() {
        super.run()
        Looper.prepare()
        handler=Handler {
            handleMessage(it)
            true
        }
        Log.d(TAG,"looper is prepared will loop.")
        Looper.loop()
        Log.d(TAG,"thread after loop() execute.")
    }

    private fun handleMessage(msg:Message?){
        handleMessageCount++
        Log.d(TAG,"handleMessage:the Looper has idle=${isIdle()}" +
                ",message what=${msg?.what},handleMessageCount=$handleMessageCount")
    }

    private fun isIdle():Boolean{
        return if( Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            handler?.looper?.queue?.isIdle?:false
        }else{
            false
        }
    }

    fun quit(){
        handler?.looper?.quit()
    }
}