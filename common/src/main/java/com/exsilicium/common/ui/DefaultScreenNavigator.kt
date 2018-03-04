package com.exsilicium.common.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.extension.safeLet
import com.exsilicium.common.utility.KeyboardHelper
import com.exsilicium.daggerextension.annotation.ActivityScope
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.screennavigator.ScreenTransaction
import javax.inject.Inject

@ActivityScope
internal class DefaultScreenNavigator @Inject constructor(
) : ScreenNavigator {

    private var activity: AppCompatActivity? = null
    private var router: Router? = null

    override fun init(activity: AppCompatActivity, rootScreen: Controller) {
        this.activity = activity
        router = (activity as BaseActivity).router.apply {
            if (!hasRootController()) setRoot(RouterTransaction.with(rootScreen))
        }
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
            if (it is BaseActivity && it.isRoot) popToRoot() else it.finish()
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

    override fun clear() {
        activity = null
        router = null
    }
}
