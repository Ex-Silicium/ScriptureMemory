package com.exsilicium.passagedetail

import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerannotations.ForScreen
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.passagedetail.service.PassageDetailRequester
import javax.inject.Inject

@ScreenScope
internal class PassageDetailPresenter @Inject constructor(
        @ForScreen private val disposableManager: DisposableManager,
        private val viewModel: PassageDetailViewModel,
        private val requester: PassageDetailRequester
) {

    fun loadPassageDetails() {
        disposableManager.add(
                requester.getDetails()
                        .doOnSubscribe { viewModel.loadingUpdated().accept(true) }
                        .doOnEvent { _, _ -> viewModel.loadingUpdated().accept(false) }
                        .subscribe(viewModel.passageUpdated(), viewModel.onError())
        )
    }
}
