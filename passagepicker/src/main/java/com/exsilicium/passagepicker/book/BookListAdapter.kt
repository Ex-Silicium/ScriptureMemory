package com.exsilicium.passagepicker.book

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.book.BookListViewHolder.OnBookClickListener
import com.exsilicium.scripture.shared.model.Book

internal class BookListAdapter(
        private val clickListener: OnBookClickListener
) : RecyclerView.Adapter<BookListViewHolder>() {
    private val data = mutableListOf<Book>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return BookListViewHolder(itemView, clickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemId(position: Int) = data[position].id.toLong()

    fun setData(books: List<Book>) {
        val diffResult = DiffUtil.calculateDiff(BookCallback(data, books))
        data.clear()
        data.addAll(books)
        diffResult.dispatchUpdatesTo(this)
    }
}
