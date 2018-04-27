package com.exsilicium.passagepicker.book

import com.exsilicium.scripture.shared.model.Book
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test

internal class BookListPresenterTest {

    private val mockViewModel = mock<BookListViewModel>()
    private val mockBooksFilteredConsumer = mock<Consumer<List<Book>>>()

    @Before
    fun setUp() {
        whenever(mockViewModel.booksFiltered()).thenReturn(mockBooksFilteredConsumer)
    }

    @Test
    fun allBooksAreFilteredOnInitialLoad() {
        BookListPresenter(mockViewModel, mock())

        verify(mockBooksFilteredConsumer).accept(Book.values().asList())
    }
}