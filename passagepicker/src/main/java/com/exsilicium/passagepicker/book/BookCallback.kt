package com.exsilicium.passagepicker.book

import android.support.v7.util.DiffUtil
import com.exsilicium.scripture.shared.model.Book

internal class BookCallback(
        private val oldList: List<Book>,
        private val newList: List<Book>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

val Book.id: Int
    get() {
        return title.hashCode()
    }
