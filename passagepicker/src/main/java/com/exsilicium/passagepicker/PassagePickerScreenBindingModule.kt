package com.exsilicium.passagepicker

import com.bluelinelabs.conductor.Controller
import com.exsilicium.common.dagger.ControllerKey
import com.exsilicium.passagepicker.book.BookListComponent
import com.exsilicium.passagepicker.book.BookListController
import com.exsilicium.passagepicker.chapter.ChapterListComponent
import com.exsilicium.passagepicker.chapter.ChapterListController
import com.exsilicium.passagepicker.verse.VerseListComponent
import com.exsilicium.passagepicker.verse.VerseListController
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            BookListComponent::class,
            ChapterListComponent::class,
            VerseListComponent::class
        ]
)
abstract class PassagePickerScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(BookListController::class)
    internal abstract fun bindBookListController(
            builder: BookListComponent.Builder
    ): AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(ChapterListController::class)
    internal abstract fun bindChapterListController(
            builder: ChapterListComponent.Builder
    ): AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(VerseListController::class)
    internal abstract fun bindVerseListController(
            builder: VerseListComponent.Builder
    ): AndroidInjector.Factory<out Controller>
}