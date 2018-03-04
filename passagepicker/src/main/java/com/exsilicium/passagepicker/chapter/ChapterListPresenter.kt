package com.exsilicium.passagepicker.chapter

import android.content.Context
import com.exsilicium.common.utility.UiUtils
import com.exsilicium.passagepicker.chapter.ChapterListViewHolder.OnChapterClickListener
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject

internal class ChapterListPresenter @Inject constructor(
        val book: Book,
        val context: Context
) : OnChapterClickListener {

    override fun onClick(chapter: Int) {
        UiUtils.notImplemented(context, "Clicked ${book.title} ${chapter + 1}")
    }
}