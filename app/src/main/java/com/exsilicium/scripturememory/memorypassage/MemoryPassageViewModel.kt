package com.exsilicium.scripturememory.memorypassage

import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.di.ScreenScope
import com.exsilicium.scripturememory.model.MemoryPassage
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
class MemoryPassageViewModel @Inject constructor(
) {
    private val loadingRelay = BehaviorRelay.create<Boolean>()
    private val errorRelay = BehaviorRelay.create<Int>()
    private val showAddButtonRelay = BehaviorRelay.create<Boolean>()
    private val memoryPassagesRelay = BehaviorRelay.create<List<MemoryPassage>>()

    init {
        showAddButtonRelay.accept(true)
    }

    fun loading(): Observable<Boolean> = loadingRelay

    fun showAddButton(): Observable<Boolean> = showAddButtonRelay

    fun memoryPassagesUpdates(): Observable<List<MemoryPassage>> = memoryPassagesRelay

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