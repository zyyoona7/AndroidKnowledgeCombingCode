package com.zyyoona7.akcc

import android.os.Bundle
import com.zyyoona7.akcc.animation.AnimationActivity
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun initVarAndViews(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initListeners(savedInstanceState: Bundle?) {
        binding.btnAnimation.setOnClickListener {
            AnimationActivity.start(this@MainActivity)
        }
    }

}
