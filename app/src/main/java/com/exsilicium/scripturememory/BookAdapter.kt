package com.exsilicium.scripturememory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.exsilicium.scripture.shared.model.Book

internal class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {

    private val values = Book.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false))

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) = holder.bind(values[position])

    override fun getItemCount() = values.size
}
