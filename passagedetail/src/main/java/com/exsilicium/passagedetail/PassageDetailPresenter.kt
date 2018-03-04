package com.exsilicium.passagedetail

import com.exsilicium.daggerextension.annotation.ScreenScope
import com.exsilicium.passagedetail.service.PassageDetailRequester
import javax.inject.Inject

@ScreenScope
internal class PassageDetailPresenter @Inject constructor(
        private val viewModel: PassageDetailViewModel,
        private val requester: PassageDetailRequester
)
