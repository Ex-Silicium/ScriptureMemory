package com.exsilicium.scripturememory.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.exsilicium.scripturememory.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class DefaultScreenNavigator @Inject constructor(

) : ScreenNavigator {

    private var router: Router? = null

    override fun initWithRouter(router: Router, rootScreen: Controller) {
        this.router = router
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen))
        }
    }

    override fun pop() = router?.handleBack() ?: false

    override fun clear() {
        router = null
    }
}
