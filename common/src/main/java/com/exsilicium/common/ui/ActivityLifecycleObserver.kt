package com.exsilicium.common.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity

abstract class ActivityLifecycleObserver : LifecycleObserver {
    var activity: AppCompatActivity? = null
        private set

    internal fun register(activity: AppCompatActivity) {
        this.activity = activity
        activity.lifecycle.addObserver(this)
    }

    internal open fun routerAttached() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onActivityDestroyed() {
        activity = null
    }
}