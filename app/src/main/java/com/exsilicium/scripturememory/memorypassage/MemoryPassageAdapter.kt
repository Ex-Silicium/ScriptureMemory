package com.exsilicium.scripturememory.memorypassage

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.model.MemoryPassage

class MemoryPassageAdapter(
        private val clickListener: MemoryPassageClickListener
) : RecyclerView.Adapter<MemoryPassageViewHolder>() {

    private val data = mutableListOf<MemoryPassage>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = MemoryPassageViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.list_item_memory_passage, parent, false),
            clickListener
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MemoryPassageViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemId(position: Int) = data[position].id.toLong()

    fun setData(memoryPassages: List<MemoryPassage>) {
        val diffResult = DiffUtil.calculateDiff(MemoryPassageCallback(data, memoryPassages))
        data.clear()
        data.addAll(memoryPassages)
        diffResult.dispatchUpdatesTo(this)
    }
}
