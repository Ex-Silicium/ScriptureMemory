package com.exsilicium.passagepicker.chapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exsilicium.passagepicker.R
import com.exsilicium.passagepicker.chapter.ChapterListViewHolder.OnChapterClickListener

internal class ChapterListAdapter(
        private val clickListener: OnChapterClickListener,
        private val chapterCount: Int
) : RecyclerView.Adapter<ChapterListViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_number, parent, false)
        return ChapterListViewHolder(itemView, clickListener)
    }

    override fun getItemCount() = chapterCount

    override fun onBindViewHolder(holder: ChapterListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int) = position.toLong()
}