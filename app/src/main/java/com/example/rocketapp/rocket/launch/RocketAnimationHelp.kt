package com.example.rocketapp.rocket.launch

import android.animation.Animator
import android.content.res.Resources
import android.view.View
import android.view.animation.AccelerateInterpolator

class RocketAnimationHelp {

    fun startRocketLaunchAnimation(view: View, onAnimationEnd: () -> Unit = {}) {
        val listener = RocketAnimationCallBack(onAnimationEnd)
        val displayMetrics = Resources.getSystem().displayMetrics
        val displayHeight: Int = displayMetrics.heightPixels
        val animationDistance: Float = -(displayHeight + view.height).toFloat()
        view
            .animate()
            .setListener(listener)
            .translationY(animationDistance)
            .setDuration(3000)
            .setInterpolator(AccelerateInterpolator())
            .start()
    }

    class RocketAnimationCallBack(val onAnimationEnd: () -> Unit) : Animator.AnimatorListener {

        override fun onAnimationStart(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            onAnimationEnd()
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationRepeat(p0: Animator?) {
        }
    }
}
