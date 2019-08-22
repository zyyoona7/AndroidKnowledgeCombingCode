package com.zyyoona7.akcc.animation

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.zyyoona7.akcc.R
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityAnimationBinding

class AnimationActivity : BindingActivity<ActivityAnimationBinding>() {

    companion object{
        fun start(context: Context){
            val intent=Intent(context,AnimationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_animation
    }

    override fun initVarAndViews(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initListeners(savedInstanceState: Bundle?) {
        binding.btnTween.setOnClickListener {
            translateByTweenAnim(it)
        }

        binding.btnProperty.setOnClickListener {
            translateByPropertyAnim(it)
        }
    }

    private fun translateByPropertyAnim(view:View){
        val translateAnimator=ObjectAnimator
            .ofFloat(view,"translationY",0f,100f)
        translateAnimator.duration=500
        translateAnimator.start()
    }

    /**
     * 补间动画源码追踪 ->View.startAnimation() ->
     */
    private fun translateByTweenAnim(view: View){
        val translateAnimation=TranslateAnimation(0f,
            0f,0f,300f)
        translateAnimation.duration=500
        view.startAnimation(translateAnimation)
        view.translationY
    }
}
