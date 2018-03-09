package com.exsilicium.common.ui

import android.content.Intent
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.extension.safeLet
import com.exsilicium.common.utility.KeyboardHelper
import com.exsilicium.daggerannotations.ActivityScope
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.screennavigator.ScreenTransaction
import javax.inject.Inject

@ActivityScope
internal class DefaultScreenNavigator @Inject constructor(
) : ActivityLifecycleObserver(), ScreenNavigator {

    private var router: Router? = null

    override fun routerAttached() {
        router = (activity as BaseActivity).router
    }

    override fun push(screenTransaction: ScreenTransaction) {
        safeLet(activity, router) { activity, router ->
            KeyboardHelper.hideKeyboard(activity)
            screenTransaction.screen.launchScreen(activity, router, screenTransaction.animationConfiguration)
        }
    }

    override fun pop() = router?.handleBack() ?: false

    override fun popTo(tag: String) {
        router?.popToTag(tag)
    }

    override fun popToRoot() {
        router?.popToRoot()
    }

    override fun popToHome() {
        activity?.let {
            if (it is BaseActivity && it.isRoot) {
                popToRoot()
            } else {
                it.finish()
            }
        }
    }

    override fun popWithResult(resultCode: Int, intent: Intent) {
        activity?.let {
            it.setResult(resultCode, intent)
            it.finish()
            it.overridePendingTransition(R.anim.no_op, (activity as BaseActivity).backAnimation)
        }
    }

    override fun getTopController(): Controller {
        router?.let {
            return it.backstack[it.backstackSize - 1].controller()
        }
        throw IllegalStateException("Router is not attached")
    }

    override fun backStackSize() = router?.backstackSize ?: -1

    override fun onActivityDestroyed() {
        super.onActivityDestroyed()
        router = null
    }
}
