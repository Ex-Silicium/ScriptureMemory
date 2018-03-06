package com.exsilicium.passagepicker.chapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.exsilicium.passagepicker.R
import kotterknife.bindView

internal class ChapterListViewHolder(
        itemView: View,
        private val clickListener: OnChapterClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val chapterNumber: TextView by bindView(R.id.tv_number)

    fun bind(chapter: Int) {
        chapterNumber.text = (chapter + 1).toString()
        chapterNumber.setOnClickListener { clickListener.onClick(chapter + 1) }
    }

    internal interface OnChapterClickListener {
        fun onClick(chapter: Int)
    }
}