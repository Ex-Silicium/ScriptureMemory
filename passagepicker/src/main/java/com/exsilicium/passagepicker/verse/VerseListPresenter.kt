package com.exsilicium.passagepicker.verse

import com.exsilicium.common.ui.ActivityStarter
import com.exsilicium.passagepicker.verse.VerseListController.Companion.KEY_CHAPTER
import com.exsilicium.passagepicker.verse.VerseListViewHolder.OnVerseClickedListener
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.Verse
import javax.inject.Inject
import javax.inject.Named

internal class VerseListPresenter @Inject constructor(
        private val book: Book,
        @field:Named(KEY_CHAPTER) private val chapter: Int,
        private val activityStarter: ActivityStarter
) : OnVerseClickedListener {

    override fun onClick(verse: Int) {
        activityStarter.loadPassage(ScriptureReference(book, Verse(chapter, verse)))
    }
}