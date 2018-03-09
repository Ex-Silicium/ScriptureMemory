package com.exsilicium.passagepicker.book

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerannotations.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            BookListModule::class,
            ScreenModule::class
        ]
)
internal interface BookListComponent : AndroidInjector<BookListController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BookListController>()
}
