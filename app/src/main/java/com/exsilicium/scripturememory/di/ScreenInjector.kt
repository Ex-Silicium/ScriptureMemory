package com.exsilicium.scripturememory.di

import android.app.Activity
import android.util.ArrayMap
import com.bluelinelabs.conductor.Controller
import com.exsilicium.scripturememory.base.BaseActivity
import com.exsilicium.scripturememory.base.BaseController
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ScreenInjector @Inject constructor(
        private val screenInjectors: Map<Class<out Controller>,
                @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Controller>>>
) {
    private val cache: MutableMap<String, AndroidInjector<Controller>> = ArrayMap()

    fun inject(controller: Controller) {
        if (controller !is BaseController) {
            throw IllegalArgumentException("Controller must extend BaseController")
        }
        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId)) {
            cache[instanceId]?.inject(controller)
        } else {
            @Suppress("UNCHECKED_CAST")
            val injectorFactory = screenInjectors[controller.javaClass] as Provider<AndroidInjector.Factory<Controller>>
            val injector = injectorFactory.get()!!.create(controller)
            cache[instanceId] = injector
            injector.inject(controller)
        }
    }

    fun clearComponent(controller: Controller) {
        cache.remove(controller.instanceId)
    }

    companion object {
        fun get(activity: Activity): ScreenInjector {
            if (activity !is BaseActivity) {
                throw IllegalArgumentException("Activity must extend BaseActivity")
            }
            return activity.screenInjector
        }
    }
}