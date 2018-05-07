package com.exsilicium.scripturememory.home

import android.support.v7.util.DiffUtil
import com.exsilicium.persistence.model.MemoryPassage

internal class MemoryPassageCallback(
        private val oldList: List<MemoryPassage>,
        private val newList: List<MemoryPassage>
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
