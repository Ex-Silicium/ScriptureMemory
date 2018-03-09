package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerannotations.ForActivity
import dagger.android.AndroidInjector

interface ActivityComponent<T> : AndroidInjector<T> {
    @ForActivity
    fun disposableManager(): DisposableManager
}