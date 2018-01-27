package com.exsilicium.scripturememory.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.ButterKnife
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.model.MemoryPassage
import kotterknife.bindView

class MemoryPassageViewHolder(
        itemView: View,
        private val clickListener: MemoryPassageClickListener
) : RecyclerView.ViewHolder(itemView) {

    private var memoryPassage: MemoryPassage? = null

    private val reference: TextView by bindView(R.id.tv_passage_reference)
    private val content: TextView by bindView(R.id.tv_passage_content)

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener { memoryPassage?.let { clickListener.onClicked(it) } }
    }

    fun bind(memoryPassage: MemoryPassage) {
        this.memoryPassage = memoryPassage
        reference.text = memoryPassage.scriptureReference.toString()
        content.text = memoryPassage.text
    }

    interface MemoryPassageClickListener {
        fun onClicked(memoryPassage: MemoryPassage)
    }
}
