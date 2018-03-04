package com.exsilicium.passagepicker.book

import com.exsilicium.daggerextension.annotation.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
internal interface BookListComponent : AndroidInjector<BookListController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BookListController>()
}
