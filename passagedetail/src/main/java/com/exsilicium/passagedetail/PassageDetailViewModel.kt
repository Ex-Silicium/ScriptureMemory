package com.exsilicium.passagedetail

import com.exsilicium.daggerannotations.ScreenScope
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
internal class PassageDetailViewModel @Inject constructor(
) {

    private val loadingRelay = BehaviorRelay.create<Boolean>()
    private val errorRelay = BehaviorRelay.createDefault(-1)
    private val passageTextRelay = BehaviorRelay.create<String>()

    fun loading(): Observable<Boolean> = loadingRelay

    fun passageUpdates(): Observable<String> = passageTextRelay

    fun errorUpdates(): Observable<Int> = errorRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay

    fun passageUpdated(): Consumer<String> {
        errorRelay.accept(-1)
        return passageTextRelay
    }

    fun onError() = Consumer { t: Throwable ->
        Timber.e(t, "Error loading memory passages")
        errorRelay.accept(R.string.error_loading_passage)
    }
}
