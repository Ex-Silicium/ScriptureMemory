package com.exsilicium.screennavigator

import android.app.Activity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

data class ControllerScreen(
        val controller: Controller,
        val pushChangeHandler: ControllerChangeHandler? = SlideChangeHandler(),
        val popChangeHandler: ControllerChangeHandler? = SlideChangeHandler(),
        val addToBackStack: Boolean = true,
        val isRoot: Boolean = false,
        val tag: String? = null
) : Screen {
    override fun launchScreen(activity: Activity, router: Router, animationConfiguration: AnimationConfiguration) {
        if (isRoot) {
            router.setRoot(RouterTransaction.with(controller))
        } else {
            val routerTransaction = RouterTransaction.with(controller)
            if (pushChangeHandler != null) {
                routerTransaction.pushChangeHandler(pushChangeHandler)
            }
            if (popChangeHandler != null) {
                routerTransaction.popChangeHandler(popChangeHandler)
            }
            if (tag != null) {
                routerTransaction.tag(tag)
            }
            if (addToBackStack) {
                router.pushController(routerTransaction)
            } else {
                router.replaceTopController(routerTransaction)
            }
        }
    }
}