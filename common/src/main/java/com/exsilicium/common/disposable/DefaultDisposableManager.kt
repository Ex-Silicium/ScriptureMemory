package com.exsilicium.common.disposable

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

internal class DefaultDisposableManager @Inject constructor(
) : DisposableManager {

    private val compositeDisposable = CompositeDisposable()

    override fun add(disposable: Disposable) {
        compositeDisposable += disposable
    }

    override fun addAll(vararg disposables: Disposable) {
        @Suppress("SpreadOperator")
        compositeDisposable.addAll(*disposables)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}