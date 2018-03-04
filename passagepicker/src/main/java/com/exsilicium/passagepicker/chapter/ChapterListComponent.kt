package com.exsilicium.passagepicker.chapter

import com.exsilicium.daggerextension.annotation.ScreenScope
import com.exsilicium.scripture.shared.model.Book
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
internal interface ChapterListComponent : AndroidInjector<ChapterListController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ChapterListController>() {
        @BindsInstance
        abstract fun book(book: Book)

        override fun seedInstance(instance: ChapterListController) {
            book(Book.values()[instance.args.getInt(ChapterListController.BOOK_ORDINAL_KEY)])
        }
    }
}