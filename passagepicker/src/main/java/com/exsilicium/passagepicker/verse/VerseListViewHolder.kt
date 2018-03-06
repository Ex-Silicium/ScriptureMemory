package com.exsilicium.passagepicker.verse

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.exsilicium.passagepicker.R
import kotterknife.bindView

internal class VerseListViewHolder(
        itemView: View,
        private val clickListener: OnVerseClickedListener
) : RecyclerView.ViewHolder(itemView) {

    private val verseNumber: TextView by bindView(R.id.tv_number)

    fun bind(verse: Int) {
        verseNumber.text = (verse + 1).toString()
        verseNumber.setOnClickListener { clickListener.onClick(verse + 1) }
    }

    internal interface OnVerseClickedListener {
        fun onClick(verse: Int)
    }
}