package com.exsilicium.passagepicker.book

import com.exsilicium.passagepicker.PassagePickerActivity.Companion.addPassageFromBook
import com.exsilicium.passagepicker.book.BookListViewHolder.OnBookClickListener
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.scripture.shared.model.Book
import javax.inject.Inject

internal class BookListPresenter @Inject constructor(
        viewModel: BookListViewModel,
        private val screenNavigator: ScreenNavigator
) : OnBookClickListener {

    init {
        viewModel.booksFiltered().accept(Book.values().toList())
    }

    override fun onClick(book: Book) {
        addPassageFromBook(screenNavigator, book)
    }
}
