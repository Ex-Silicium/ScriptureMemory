package com.exsilicium.passageswidget

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WidgetsModule {

    @ContributesAndroidInjector
    abstract fun contributesPassagesWidget(): PassagesWidgetProvider
}