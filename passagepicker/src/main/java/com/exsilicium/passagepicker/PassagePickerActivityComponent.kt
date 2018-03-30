package com.exsilicium.passagepicker

import com.exsilicium.common.dagger.ActivityComponent
import com.exsilicium.common.dagger.ActivityModule
import com.exsilicium.common.ui.ActivityViewInterceptorModule
import com.exsilicium.common.ui.NavigationModule
import com.exsilicium.daggerannotations.ActivityScope
import com.exsilicium.passagepicker.book.BookListDebugDrawerCustomizationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            ActivityModule::class,
            NavigationModule::class,
            PassagePickerScreenBindingModule::class,
            ActivityViewInterceptorModule::class,
            BookListDebugDrawerCustomizationModule::class // todo find a way to scope this by screen not activity
        ] // todo finish changes and see more ideas at https://github.com/Ex-Silicium/ScriptureMemory/issues/5
)
interface PassagePickerActivityComponent : ActivityComponent<PassagePickerActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassagePickerActivity>()
}