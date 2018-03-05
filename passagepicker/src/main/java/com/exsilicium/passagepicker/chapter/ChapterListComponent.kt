package com.exsilicium.passagepicker.chapter

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerextension.annotation.ScreenScope
import com.exsilicium.scripture.shared.model.Book
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            ChapterListModule::class,
            ScreenModule::class
        ]
)
internal interface ChapterListComponent : AndroidInjector<ChapterListController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ChapterListController>() {
        @BindsInstance
        abstract fun book(book: Book)

        override fun seedInstance(instance: ChapterListController) {
            book(Book.values()[instance.args.getInt(ChapterListController.KEY_BOOK_ORDINAL)])
        }
    }
}