package com.exsilicium.screennavigator

import android.support.annotation.AnimRes

data class AnimationConfiguration(
        @AnimRes internal val entering: Int,
        @AnimRes internal val exiting: Int,
        @AnimRes internal val popEntering: Int,
        @AnimRes internal val popExiting: Int
) {

    companion object {
        private const val UNSET = 0

        fun dropToReveal(): AnimationConfiguration {
            return AnimationConfiguration(UNSET, R.anim.slide_down, UNSET, UNSET)
        }

        fun slideIn(): AnimationConfiguration {
            return AnimationConfiguration(
                    R.anim.slide_in_from_right, R.anim.no_op, R.anim.no_op, R.anim.slide_out_to_right
            )
        }

        fun modal(): AnimationConfiguration {
            return AnimationConfiguration(R.anim.slide_up, R.anim.no_op, R.anim.no_op, R.anim.slide_down)
        }

        fun modalDrop(): AnimationConfiguration {
            return AnimationConfiguration(R.anim.slide_up, R.anim.slide_down_slow, R.anim.no_op, R.anim.slide_down)
        }
    }
}