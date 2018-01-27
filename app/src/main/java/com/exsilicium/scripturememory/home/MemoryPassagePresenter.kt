package com.exsilicium.scripturememory.home

import android.content.Context
import com.exsilicium.scripturememory.di.ScreenScope
import com.exsilicium.scripturememory.helper.UiUtils
import com.exsilicium.scripturememory.model.MemoryPassage
import javax.inject.Inject

@ScreenScope
class MemoryPassagePresenter @Inject constructor(
        private val context: Context,
        private val viewModel: MemoryPassageViewModel,
        private val memoryPassagesRequester: MemoryPassagesRequester
) : MemoryPassageViewHolder.MemoryPassageClickListener {

    init {
        loadMemoryPassages()
    }

    private fun loadMemoryPassages() {
        memoryPassagesRequester.getMemoryPassages()
                .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                .doOnEvent { _, _ -> viewModel.loadingUpdated().accept(false) }
                .subscribe(viewModel.memoryPassagesUpdated(), viewModel.onError())
    }

    override fun onClicked(memoryPassage: MemoryPassage) {
        UiUtils.notImplemented(context, "Show memory passage detail screen")
    }
}
