package com.exsilicium.scripturememory.home

import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.model.MemoryPassage
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
internal class MemoryPassageViewModel @Inject constructor(
) {

    private val loadingRelay = BehaviorRelay.create<Boolean>()
    private val memoryPassagesRelay = BehaviorRelay.create<List<MemoryPassage>>()
    private val errorRelay = BehaviorRelay.create<Int>()

    fun loading(): Observable<Boolean> = loadingRelay

    fun memoryPassagesUpdates(): Observable<List<MemoryPassage>> = memoryPassagesRelay

    fun errorUpdates(): Observable<Int> = errorRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay

    fun memoryPassagesUpdated(): Consumer<List<MemoryPassage>> {
        errorRelay.accept(-1)
        return memoryPassagesRelay
    }

    fun onError() = Consumer<Throwable> { t ->
        Timber.e(t, "Error loading memory passages")
        errorRelay.accept(R.string.error_loading_memory_passages)
    }
}
