package com.exsilicium.passagepicker.verse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exsilicium.passagepicker.R

internal class VerseListAdapter(
        private val clickListener: VerseListViewHolder.OnVerseClickedListener,
        private val verseCount: Int
) : RecyclerView.Adapter<VerseListViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_number, parent, false)
        return VerseListViewHolder(itemView, clickListener)
    }

    override fun getItemCount() = verseCount

    override fun onBindViewHolder(holder: VerseListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int) = position.toLong()
}