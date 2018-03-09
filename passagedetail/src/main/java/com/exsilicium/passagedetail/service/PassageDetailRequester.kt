package com.exsilicium.passagedetail.service

import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.scripture.shared.model.ScriptureReference
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ScreenScope
internal class PassageDetailRequester @Inject constructor(
        private val reference: ScriptureReference,
        private val passageService: PassageService
) {

    fun getDetails(): Single<String> = passageService.getPassage(reference)
            .subscribeOn(Schedulers.io())
            .map { response -> response.passage() }
}
