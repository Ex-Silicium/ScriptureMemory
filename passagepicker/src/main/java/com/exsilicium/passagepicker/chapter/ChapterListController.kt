package com.exsilicium.passagepicker.chapter

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.common.ui.GridHelper
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.R2
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject

internal class ChapterListController(
        args: Bundle? = null
) : BaseController(args) {

    @Inject lateinit var book: Book
    @Inject lateinit var presenter: ChapterListPresenter
    @Inject lateinit var gridHelper: GridHelper

    @BindView(R2.id.recycler_view) lateinit var recyclerView: RecyclerView

    private lateinit var adapter: ChapterListAdapter

    override val layoutRes = R.layout.screen_chapter_list_view

    override fun title() = resourceRetriever.getString(R.string.select_chapter_in_format, book.title)

    override fun onInjected() {
        super.onInjected()
        adapter = ChapterListAdapter(presenter, book.chapterCount)
    }

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = GridLayoutManager(
                view.context,
                gridHelper.getSpanCount(R.dimen.width_grid_chapter_list_item, spanCountMultiple = 5)
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
        const val KEY_BOOK_ORDINAL = "book"

        private const val KEY_SCROLL_TO_POSITION = "firstCompletelyVisibleItemPosition"

        fun addPassage(book: Book): ChapterListController {
            return ChapterListController(Bundle().apply {
                putInt(KEY_BOOK_ORDINAL, book.ordinal)
            })
        }
    }
}