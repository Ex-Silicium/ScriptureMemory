package com.exsilicium.common.base

import android.app.Application
import com.exsilicium.common.dagger.ActivityInjector
import javax.inject.Inject

abstract class BaseApplication : Application() {
    @Inject lateinit var activityInjector: ActivityInjector
}