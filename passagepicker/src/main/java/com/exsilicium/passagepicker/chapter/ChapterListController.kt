package com.exsilicium.passagepicker.chapter

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.R2
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject

internal class ChapterListController(
        args: Bundle? = null
) : BaseController(args) {

    @Inject lateinit var book: Book
    @Inject lateinit var presenter: ChapterListPresenter

    @BindView(R2.id.recycler_view) lateinit var recyclerView: RecyclerView

    override val layoutRes = R.layout.screen_chapter_list_view

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = GridLayoutManager(view.context, SPAN_COUNT)
        recyclerView.adapter = ChapterListAdapter(presenter, book.chapterCount)
    }

    internal companion object {
        const val BOOK_ORDINAL_KEY = "book"
        private const val SPAN_COUNT = 5

        fun addPassage(book: Book): ChapterListController {
            return ChapterListController(Bundle().apply {
                putInt(BOOK_ORDINAL_KEY, book.ordinal)
            })
        }
    }
}