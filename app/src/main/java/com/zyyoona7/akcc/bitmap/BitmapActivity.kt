package com.zyyoona7.akcc.bitmap

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.zyyoona7.akcc.R
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityBitmapBinding
import java.util.*


private const val TAG = "BitmapActivity"

class BitmapActivity : BindingActivity<ActivityBitmapBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BitmapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_bitmap
    }

    override fun initVarAndViews(savedInstanceState: Bundle?) {
        val options=BitmapFactory.Options()
//        options.inPreferredConfig=Bitmap.Config.RGB_565
        val flowerBitmap = BitmapFactory.decodeStream(assets.open("flower.jpg"),Rect(),options)
        Log.d(TAG, "flower bitmap byteCount=${byteSize(flowerBitmap)},is mutable=${flowerBitmap?.isMutable}")
        val emptyBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.RGB_565)
        Log.d(TAG, "empty bitmap byteCount=${byteSize(emptyBitmap)},is mutable=${emptyBitmap.isMutable}")
        //decode 出来的 Bitmap 都是不可修改的，所以这里会抛出异常
        //java.lang.IllegalStateException: Immutable bitmap passed to Canvas constructor
//        val canvas=Canvas(flowerBitmap)
//        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        paint.isFilterBitmap = true
//        paint.isDither = true
//        canvas.drawColor(Color.RED)
        binding.imageView.setImageBitmap(flowerBitmap)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initListeners(savedInstanceState: Bundle?) {
    }

    private fun byteCount(bitmap: Bitmap?): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap?.allocationByteCount ?: 0
        } else {
            bitmap?.byteCount ?: 0
        }
    }

    private fun byteSize(bitmap: Bitmap?): String {
        val byteNum = byteCount(bitmap)
        return when {
            byteNum < 0 -> "shouldn't be less than zero!"
            byteNum < 1024 -> String.format(Locale.getDefault(), "%.3fB", byteNum.toDouble())
            byteNum < 1048576 -> String.format(
                Locale.getDefault(),
                "%.3fKB",
                byteNum.toDouble() / 1024
            )
            byteNum < 1073741824 -> String.format(
                Locale.getDefault(),
                "%.3fMB",
                byteNum.toDouble() / 1048576
            )
            else -> String.format(Locale.getDefault(), "%.3fGB", byteNum.toDouble() / 1073741824)
        }
    }
}
