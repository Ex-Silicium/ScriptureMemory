package com.exsilicium.scripturememory.picker.book

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.base.BaseController
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookListController : BaseController() {
    @Inject lateinit var presenter: BookListPresenter
    @Inject lateinit var viewModel: BookListViewModel

    @BindView(R.id.et_filter_books) lateinit var filterBooks: TextView
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.tv_message) lateinit var errorMessage: TextView

    override val layoutRes = R.layout.screen_book_list_view

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
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        recyclerView.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
                        errorMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                        (recyclerView.adapter as BookListAdapter).setData(it)
                    }
    )

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = BookListAdapter(presenter)
    }

    companion object {
        fun addPassage() = BookListController().apply {
            overridePushHandler(HorizontalChangeHandler())
            overridePopHandler(HorizontalChangeHandler())
        }
    }
}
