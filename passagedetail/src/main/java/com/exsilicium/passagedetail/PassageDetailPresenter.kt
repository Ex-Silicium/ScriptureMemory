package com.exsilicium.passagedetail

import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerextension.annotation.ForScreen
import com.exsilicium.daggerextension.annotation.ScreenScope
import com.exsilicium.passagedetail.service.PassageDetailRequester
import javax.inject.Inject

@ScreenScope
internal class PassageDetailPresenter @Inject constructor(
        @ForScreen private val disposableManager: DisposableManager,
        private val viewModel: PassageDetailViewModel,
        private val requester: PassageDetailRequester
) {

    init {
        loadPassageDetails()
    }

    private fun loadPassageDetails() {
        disposableManager.add(
                requester.getDetails()
                        .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                        .doOnEvent { _, _ -> viewModel.loadingUpdated().accept(false) }
                        .subscribe(viewModel.passageUpdated(), viewModel.onError())
        )
    }
}
