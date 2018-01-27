package com.exsilicium.scripturememory.picker.book

import com.exsilicium.scripturememory.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface BookListComponent : AndroidInjector<BookListController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BookListController>()
}
