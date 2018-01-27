package com.exsilicium.scripturememory.passagedetail

import com.exsilicium.scripturememory.di.ScreenScope
import javax.inject.Inject

@ScreenScope
class PassageDetailPresenter @Inject constructor(
        private val viewModel: PassageDetailViewModel,
        private val requester: PassageDetailRequester
)
