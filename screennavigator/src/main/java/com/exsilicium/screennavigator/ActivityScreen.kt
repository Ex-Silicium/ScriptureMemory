package com.exsilicium.screennavigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat.makeCustomAnimation
import com.bluelinelabs.conductor.Router
import kotlin.reflect.KClass

data class ActivityScreen(
        private val activityClass: KClass<out Activity>,
        private val args: Bundle? = null,
        private val intentProcessor: IntentProcessor? = null,
        private val requestCode: Int? = null
) : Screen {

    override fun launchScreen(
            activity: Activity,
            router: Router,
            animationConfiguration: AnimationConfiguration
    ) {
        val intent = Intent(activity, activityClass.java)

        args?.let {
            intent.putExtras(it)
        }

        intentProcessor?.processIntent(intent)

        intent.putExtra(EXTRA_BACK_ANIMATION, animationConfiguration.popExiting)

        val options = makeCustomAnimation(activity, animationConfiguration.entering, animationConfiguration.exiting)

        if (requestCode != null) {
            activity.startActivityForResult(intent, requestCode, options.toBundle())
        } else {
            activity.startActivity(intent, options.toBundle())
        }
    }

    companion object {
        const val EXTRA_BACK_ANIMATION = "back_animation"
    }
}
