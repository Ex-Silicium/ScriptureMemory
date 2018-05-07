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
    private val passageTextRelay = BehaviorRelay.create<String>()
    private val passageInMyPassagesRelay = BehaviorRelay.create<Boolean>()
    private val errorRelay = BehaviorRelay.create<Int>()

    fun loading(): Observable<Boolean> = loadingRelay

    fun passageUpdates(): Observable<String> = passageTextRelay

    fun inMyPassagesUpdates(): Observable<Boolean> = passageInMyPassagesRelay

    fun errorUpdates(): Observable<Int> = errorRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay

    fun passageUpdated(): Consumer<String> {
        errorRelay.accept(-1)
        return passageTextRelay
    }

    fun inMyPassagesUpdate(): Consumer<Boolean> = passageInMyPassagesRelay

    fun onError() = Consumer { t: Throwable ->
        Timber.e(t, "Error loading memory passages")
        errorRelay.accept(R.string.error_loading_passage)
    }
}
