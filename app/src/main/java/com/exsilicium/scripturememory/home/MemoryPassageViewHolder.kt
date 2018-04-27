package com.exsilicium.scripturememory.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.model.MemoryPassage
import kotterknife.bindView

internal class MemoryPassageViewHolder(
        itemView: View,
        private val clickListener: MemoryPassageClickListener
) : RecyclerView.ViewHolder(itemView) {

    private var memoryPassage: MemoryPassage? = null

    private val reference: TextView by bindView(R.id.tv_passage_reference)
    private val content: TextView by bindView(R.id.tv_passage_content)

    init {
        itemView.setOnClickListener { memoryPassage?.let { clickListener.onClicked(it) } }
    }

    fun bind(memoryPassage: MemoryPassage) {
        this.memoryPassage = memoryPassage
        reference.text = memoryPassage.scriptureReference.toString()
        content.text = memoryPassage.text
    }

    internal interface MemoryPassageClickListener {
        fun onClicked(memoryPassage: MemoryPassage)
    }
}
