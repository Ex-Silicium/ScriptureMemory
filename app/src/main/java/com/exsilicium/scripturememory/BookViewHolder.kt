package com.exsilicium.scripturememory

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.exsilicium.scripture.shared.model.Book
import kotterknife.bindView

internal class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val colorView: View by bindView(R.id.view_color)
    private val bookTitleTextView: TextView by bindView(R.id.tv_book_name)

    private lateinit var book: Book

    init {
        view.setOnClickListener {
            Toast.makeText(
                    it.context,
                    it.context.getString(R.string.abbreviations_format, book.abbreviations.toString()),
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    fun bind(book: Book) {
        this.book = book
        colorView.setBackgroundColor(ContextCompat.getColor(colorView.context, getColorResForBook(book)))
        bookTitleTextView.text = book.title
    }

    companion object {
        @ColorRes private fun getColorResForBook(book: Book) =
                if (book.isOldTestament()) R.color.colorAccent else R.color.colorPrimary
    }
}
