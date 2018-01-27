package com.exsilicium.scripturememory.picker.book

import android.content.Context
import android.widget.Toast
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.picker.book.BookListViewHolder.OnBookClickListener
import javax.inject.Inject

class BookListPresenter @Inject constructor(
        private val context: Context,
        viewModel: BookListViewModel
) : OnBookClickListener {

    init {
        viewModel.booksFiltered().accept(Book.values().toList())
    }

    override fun onClick(book: Book) {
        Toast.makeText(
                context,
                context.getString(R.string.abbreviations_format, book.abbreviations.toString()),
                Toast.LENGTH_LONG
        ).show()
    }
}
