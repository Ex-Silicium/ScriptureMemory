package com.exsilicium.common.dagger

import android.app.Activity
import android.content.Context
import android.util.ArrayMap
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.base.BaseApplication
import dagger.android.AndroidInjector
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ActivityInjector(
        private val activityInjectors: Map<Class<out Activity>, Provider<AndroidInjector.Factory<out Activity>>>,
        private val lifecyclePlugin: ComponentLifecyclePlugin
) {

    private val cache: MutableMap<String, AndroidInjector<Activity>> = ArrayMap()

    internal fun inject(activity: Activity) {
        if (activity !is BaseActivity) throw IllegalArgumentException("Activity must extend BaseActivity")
        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId)) {
            cache[instanceId]?.inject(activity)
        } else {
            @Suppress("UNCHECKED_CAST")
            val injectorFactory = activityInjectors[activity.javaClass] as Provider<AndroidInjector.Factory<Activity>>
            val injector = injectorFactory.get()!!.create(activity)
            cache[instanceId] = injector
            lifecyclePlugin.componentCreated(activity.instanceId, injector)
            injector.inject(activity)
        }
    }

    internal fun clear(activity: Activity) {
        if (activity !is BaseActivity) throw IllegalArgumentException("Activity must extend BaseActivity")
        lifecyclePlugin.componentDestroyed(activity.instanceId, cache.remove(activity.instanceId))
    }

    companion object {
        fun get(context: Context) = (context.applicationContext as BaseApplication).activityInjector
    }
}