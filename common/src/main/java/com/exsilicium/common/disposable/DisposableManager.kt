package com.exsilicium.common.disposable

import io.reactivex.disposables.Disposable

interface DisposableManager {
    fun add(disposable: Disposable)

    fun addAll(vararg disposables: Disposable)

    fun clear()
}