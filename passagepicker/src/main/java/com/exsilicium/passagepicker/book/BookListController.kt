package com.exsilicium.passagepicker.book

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.R2
import com.exsilicium.scripture.shared.model.Book
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class BookListController : BaseController() {
    @Inject lateinit var presenter: BookListPresenter
    @Inject lateinit var viewModel: BookListViewModel

    @BindView(R2.id.et_filter_books) lateinit var filterBooks: TextView
    @BindView(R2.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R2.id.tv_message) lateinit var errorMessage: TextView

    override val layoutRes = R.layout.screen_book_list_view

    private lateinit var adapter: BookListAdapter

    override fun title() = resourceRetriever.getString(R.string.select_book)

    override fun onInjected() {
        super.onInjected()
        adapter = BookListAdapter(presenter)
    }

    override fun subscriptions() = arrayOf(
            RxTextView.textChanges(filterBooks)
                    .observeOn(Schedulers.computation())
                    .subscribe(viewModel.filterBooksUpdated()),
            viewModel.filterBooksUpdates()
                    .map { it.toString().toLowerCase() }
                    .subscribe { filterTerm ->
                        viewModel.booksFiltered().accept(Book.values().filter {
                            it.title.toLowerCase().contains(filterTerm) || it.abbreviations.any {
                                it.toLowerCase().contains(filterTerm)
                            }
                        })
                    },
            viewModel.filterBooksUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { filterTerm ->
                        errorMessage.text = applicationContext?.getString(R.string.no_books_for_format, filterTerm)
                    },
            viewModel.bookUpdates()
                    .distinctUntilChanged()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        recyclerView.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
                        errorMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                        (recyclerView.adapter as BookListAdapter).setData(it)
                    }
    )

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        outState.putParcelable(KEY_LIST_STATE, recyclerView.layoutManager.onSaveInstanceState())
    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        recyclerView.layoutManager.onRestoreInstanceState(savedViewState.getParcelable(KEY_LIST_STATE))
    }

    companion object {
        private const val KEY_LIST_STATE = "listState"
    }
}
