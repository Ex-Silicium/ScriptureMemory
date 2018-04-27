package com.exsilicium.passagedetail

import com.exsilicium.common.dagger.TestActivityModule
import com.exsilicium.common.ui.NavigationModule
import com.exsilicium.daggerannotations.ActivityScope
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.Verse
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            TestActivityModule::class,
            NavigationModule::class,
            TestPassageDetailScreenBindingModule::class
        ]
)
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
interface TestPassageDetailActivityComponent : AndroidInjector<PassageDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailActivity>() {

        @BindsInstance
        @Suppress("OptionalAbstractKeyword")
        // See https://github.com/arturbosch/detekt/issues/783. This should be fixed in the next detekt release.
        abstract fun bindReference(scriptureReference: ScriptureReference)

        override fun seedInstance(instance: PassageDetailActivity) {
            val book = Book.values()[instance.intent.getIntExtra(
                    PassageDetailController.KEY_BOOK_ORDINAL, 0
            )]
            val chapter = instance.intent.getIntExtra(
                    PassageDetailController.KEY_CHAPTER, 0
            )
            val verseNumber = instance.intent.getIntExtra(
                    PassageDetailController.KEY_VERSE, 0
            )
            bindReference(ScriptureReference(book, Verse(chapter, verseNumber)))
        }
    }
}