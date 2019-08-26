package com.zyyoona7.akcc.animation

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.zyyoona7.akcc.R
import com.zyyoona7.akcc.base.BindingActivity
import com.zyyoona7.akcc.databinding.ActivityAnimationBinding

/**
 * Animation duration constants
 */
private const val ANIM_DURATION = 1500L

class AnimationActivity : BindingActivity<ActivityAnimationBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AnimationActivity::class.java)
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
//            scaleByTweenAnim(it)
        }

        binding.btnProperty.setOnClickListener {
            translateByPropertyAnim(it)
        }
    }

    private fun translateByPropertyAnim(view: View) {
        val translateAnimator = ObjectAnimator
            .ofFloat(view, "translationY", 0f, 300f)
        translateAnimator.duration = ANIM_DURATION
        translateAnimator.start()
    }

    /**
     * 补间动画源码追踪 ->View.startAnimation() ->
     */
    private fun translateByTweenAnim(view: View) {
        val translateAnimation = TranslateAnimation(
            0f, 0f, 0f, -300f
        )
        translateAnimation.duration = ANIM_DURATION
        view.startAnimation(translateAnimation)
    }

    private fun scaleByTweenAnim(view: View) {
        val scaleAnimation = ScaleAnimation(
            1f, 1.3f, 1f,
            1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = ANIM_DURATION
        view.startAnimation(scaleAnimation)
    }
}
