package com.exsilicium.common.base

import android.app.Application
import android.content.BroadcastReceiver
import com.exsilicium.common.dagger.ActivityInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasBroadcastReceiverInjector
import javax.inject.Inject

abstract class BaseApplication : Application(), HasBroadcastReceiverInjector {
    @Inject lateinit var activityInjector: ActivityInjector
    @Inject lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> = broadcastReceiverInjector
}