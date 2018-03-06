package com.exsilicium.passagepicker.verse

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.common.ui.GridHelper
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.R2
import com.exsilicium.passagepicker.chapter.ChapterListController.Companion.KEY_BOOK_ORDINAL
import com.exsilicium.passagepicker.chapter.ChapterListController.Companion.KEY_SCROLL_TO_POSITION
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject
import javax.inject.Named

internal class VerseListController(
        args: Bundle? = null
) : BaseController(args) {
    @Inject lateinit var book: Book
    @Inject @Named(KEY_CHAPTER) @JvmField var chapter: Int = 0
    @Inject lateinit var presenter: VerseListPresenter
    @Inject lateinit var gridHelper: GridHelper

    @BindView(R2.id.recycler_view) lateinit var recyclerView: RecyclerView

    private lateinit var adapter: VerseListAdapter

    override val layoutRes = R.layout.screen_number_list_view

    override fun title() = resourceRetriever.getString(R.string.select_verse_in_format, book.title, chapter)

    override fun onInjected() {
        super.onInjected()
        adapter = VerseListAdapter(presenter, book.versesInChapter(chapter))
    }

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = GridLayoutManager(
                view.context,
                gridHelper.getSpanCount(R.dimen.width_grid_number_list_item, spanCountMultiple = 5)
        )
        recyclerView.adapter = adapter
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        outState.putInt(
                KEY_SCROLL_TO_POSITION,
                (recyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
        )
    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        recyclerView.scrollToPosition(savedViewState.getInt(KEY_SCROLL_TO_POSITION, 0))
    }

    internal companion object {
        const val KEY_CHAPTER = "chapter"

        fun addPassage(book: Book, chapter: Int) = VerseListController(Bundle().apply {
            putInt(KEY_BOOK_ORDINAL, book.ordinal)
            putInt(KEY_CHAPTER, chapter)
        })
    }
}