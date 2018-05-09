package com.exsilicium.passagepicker.verse

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.passagepicker.chapter.ChapterListController.Companion.KEY_BOOK_ORDINAL
import com.exsilicium.passagepicker.verse.VerseListController.Companion.KEY_CHAPTER
import com.exsilicium.scripture.shared.model.Book
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

@ScreenScope
@Subcomponent(
        modules = [
            VerseListModule::class,
            ScreenModule::class
        ]
)
internal interface VerseListComponent : AndroidInjector<VerseListController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<VerseListController>() {

        @BindsInstance
        abstract fun bindBook(book: Book)

        @BindsInstance
        @Named(KEY_CHAPTER)
        abstract fun bindChapter(chapter: Int)

        override fun seedInstance(instance: VerseListController) {
            bindBook(Book.values()[instance.args.getInt(KEY_BOOK_ORDINAL)])
            bindChapter(instance.args.getInt(KEY_CHAPTER))
        }
    }
}
