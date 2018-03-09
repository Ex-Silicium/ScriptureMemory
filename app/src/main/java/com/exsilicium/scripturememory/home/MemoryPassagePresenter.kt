package com.exsilicium.scripturememory.home

import android.content.Context
import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.common.utility.UiUtils
import com.exsilicium.daggerannotations.ForScreen
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.scripturememory.home.model.MemoryPassage
import javax.inject.Inject

@ScreenScope
internal class MemoryPassagePresenter @Inject constructor(
        @ForScreen private val disposableManager: DisposableManager,
        private val context: Context,
        private val viewModel: MemoryPassageViewModel,
        private val memoryPassagesRequester: MemoryPassagesRequester
) : MemoryPassageViewHolder.MemoryPassageClickListener {

    init {
        loadMemoryPassages()
    }

    private fun loadMemoryPassages() {
        disposableManager.add(
                memoryPassagesRequester.getMemoryPassages()
                        .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                        .doOnEvent { _, _ -> viewModel.loadingUpdated().accept(false) }
                        .subscribe(viewModel.memoryPassagesUpdated(), viewModel.onError())
        )
    }

    override fun onClicked(memoryPassage: MemoryPassage) {
        UiUtils.notImplemented(context, "Show memory passage detail screen")
    }
}
