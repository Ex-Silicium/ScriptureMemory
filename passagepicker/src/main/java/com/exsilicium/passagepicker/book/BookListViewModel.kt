package com.exsilicium.passagepicker.book

import com.exsilicium.scripture.shared.model.Book
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

internal class BookListViewModel @Inject constructor(
) {
    private val booksRelay = BehaviorRelay.create<List<Book>>()
    private val filterBooksRelay = BehaviorRelay.create<CharSequence>()

    fun bookUpdates(): Observable<List<Book>> = booksRelay

    fun filterBooksUpdates(): Observable<CharSequence> = filterBooksRelay

    fun booksFiltered(): Consumer<List<Book>> = booksRelay

    fun filterBooksUpdated(): Consumer<CharSequence> = filterBooksRelay
}
