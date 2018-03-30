package com.exsilicium.passagedetail

import com.exsilicium.common.dagger.ActivityComponent
import com.exsilicium.common.dagger.ActivityModule
import com.exsilicium.common.ui.ActivityViewInterceptorModule
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
            ActivityModule::class,
            NavigationModule::class,
            PassageDetailScreenBindingModule::class,
            ActivityViewInterceptorModule::class
        ]
)
interface PassageDetailActivityComponent : ActivityComponent<PassageDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailActivity>() {

        @BindsInstance
        @Suppress("OptionalAbstractKeyword")
        // See https://github.com/arturbosch/detekt/issues/783. This should be fixed in the next detekt release.
        abstract fun reference(scriptureReference: ScriptureReference)

        override fun seedInstance(instance: PassageDetailActivity) {
            val book = Book.values()[instance.intent.getIntExtra(PassageDetailController.KEY_BOOK_ORDINAL, 0)]
            val chapter = instance.intent.getIntExtra(PassageDetailController.KEY_CHAPTER, 0)
            val verseNumber = instance.intent.getIntExtra(PassageDetailController.KEY_VERSE, 0)
            reference(ScriptureReference(book, Verse(chapter, verseNumber)))
        }
    }
}