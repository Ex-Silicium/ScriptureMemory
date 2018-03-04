package com.exsilicium.screennavigator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler

class SlideChangeHandler(
        private val slideBoth: Boolean = false
) : AnimatorChangeHandler() {

    override fun getAnimator(
            container: ViewGroup,
            from: View?,
            to: View?,
            isPush: Boolean,
            toAddedToContainer: Boolean
    ): Animator {
        val animatorSet = AnimatorSet()

        if (isPush) {
            if (to != null) {
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, to.width.toFloat(), 0f))
            }
            if (slideBoth && from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, 0f, -from.width.toFloat()))
            }
        } else {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, from.width.toFloat()))
            }
            if (slideBoth && to != null) {
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, -to.width.toFloat(), 0f))
            }
        }

        return animatorSet
    }

    override fun resetFromView(from: View) {
        from.translationX = 0f
    }
}
