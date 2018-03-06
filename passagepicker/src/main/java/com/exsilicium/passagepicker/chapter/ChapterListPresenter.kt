package com.exsilicium.passagepicker.chapter

import com.exsilicium.passagepicker.PassagePickerActivity
import com.exsilicium.passagepicker.chapter.ChapterListViewHolder.OnChapterClickListener
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject

internal class ChapterListPresenter @Inject constructor(
        private val book: Book,
        private val screenNavigator: ScreenNavigator
) : OnChapterClickListener {

    override fun onClick(chapter: Int) {
        PassagePickerActivity.addPassageFromChapter(screenNavigator, book, chapter)
    }
}