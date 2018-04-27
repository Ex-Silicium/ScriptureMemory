package com.exsilicium.passagepicker.book

import com.exsilicium.scripture.shared.model.Book
import org.junit.Before
import org.junit.Test

internal class BookListViewModelTest {

    private lateinit var viewModel: BookListViewModel

    @Before
    fun setUp() {
        viewModel = BookListViewModel()
    }

    @Test
    fun bookUpdates() {
        val testObserver = viewModel.bookUpdates().test()
        val zBooks = listOf(
                Book.ZECHARIAH,
                Book.ZEPHANIAH,
                Book.EZEKIEL,
                Book.EZRA
        )

        viewModel.booksFiltered().accept(Book.values().asList())
        viewModel.booksFiltered().accept(zBooks)

        testObserver.assertValues(
                Book.values().asList(),
                zBooks
        )
    }

    @Test
    fun filterBooksUpdates() {
        val testObserver = viewModel.filterBooksUpdates().test()

        viewModel.filterBooksUpdated().accept("z")
        viewModel.filterBooksUpdated().accept("")

        testObserver.assertValues("z", "")
    }
}