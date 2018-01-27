package com.exsilicium.scripturememory.picker.book

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.extensions.color
import kotterknife.bindView

class BookListViewHolder(
        itemView: View,
        clickListener: OnBookClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val colorView: View by bindView(R.id.view_color)
    private val title: TextView by bindView(R.id.tv_book_name)

    private lateinit var book: Book

    init {
        itemView.setOnClickListener {
            clickListener.onClick(book)
        }
    }

    fun bind(book: Book) {
        this.book = book
        colorView.setBackgroundColor(ContextCompat.getColor(colorView.context, book.color))
        title.text = book.title
    }

    interface OnBookClickListener {
        fun onClick(book: Book)
    }
}
