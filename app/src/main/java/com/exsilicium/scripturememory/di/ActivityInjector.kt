package com.exsilicium.scripturememory.di

import android.app.Activity
import android.content.Context
import android.util.ArrayMap
import com.exsilicium.scripturememory.base.BaseActivity
import com.exsilicium.scripturememory.base.MyApplication
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjector @Inject constructor(
        private val activityInjectors: Map<Class<out Activity>,
                @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>
) {

    private val cache: MutableMap<String, AndroidInjector<Activity>> = ArrayMap()

    fun inject(activity: Activity) {
        if (activity !is BaseActivity) {
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }
        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId)) {
            cache[instanceId]?.inject(activity)
        } else {
            @Suppress("UNCHECKED_CAST")
            val injectorFactory = activityInjectors[activity.javaClass] as Provider<AndroidInjector.Factory<Activity>>
            val injector = injectorFactory.get()!!.create(activity)
            cache[instanceId] = injector
            injector.inject(activity)
        }
    }

    fun clear(activity: Activity) {
        if (activity !is BaseActivity) {
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }
        cache.remove(activity.instanceId)
    }

    companion object {
        fun get(context: Context): ActivityInjector {
            return (context.applicationContext as MyApplication).activityInjector
        }
    }
}