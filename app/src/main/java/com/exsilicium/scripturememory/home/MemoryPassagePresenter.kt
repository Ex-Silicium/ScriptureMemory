package com.exsilicium.scripturememory.home

import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerannotations.ForScreen
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.passagedetail.PassageDetailActivity
import com.exsilicium.persistence.model.MemoryPassage
import com.exsilicium.screennavigator.ScreenNavigator
import javax.inject.Inject
import javax.inject.Provider

@ScreenScope
internal class MemoryPassagePresenter @Inject constructor(
        @ForScreen private val disposableManager: DisposableManager,
        private val viewModel: MemoryPassageViewModel,
        private val passageRepository: MemoryPassageRepository,
        private val screenNavigatorProvider: Provider<ScreenNavigator>
) : MemoryPassageViewHolder.MemoryPassageClickListener {

    fun loadMemoryPassages() {
        disposableManager.add(
                passageRepository.getMemoryPassages()
                        .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                        .doOnError { viewModel.loadingUpdated().accept(false) }
                        .doOnNext { viewModel.loadingUpdated().accept(false) }
                        .subscribe(viewModel.memoryPassagesUpdated(), viewModel.onError())
        )
    }

    override fun onClicked(memoryPassage: MemoryPassage) {
        PassageDetailActivity.loadPassage(screenNavigatorProvider.get(), memoryPassage.reference)
    }
}
