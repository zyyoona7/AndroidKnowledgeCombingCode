package com.zyyoona7.akcc.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<VDB:ViewDataBinding>:AppCompatActivity() {

    lateinit var binding:VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId()!=0) {
            binding=DataBindingUtil.setContentView(this,getLayoutId())
        }
        initVarAndViews(savedInstanceState)
        initListeners(savedInstanceState)
        initData(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutId():Int

    abstract fun initVarAndViews(savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initListeners(savedInstanceState: Bundle?)
}