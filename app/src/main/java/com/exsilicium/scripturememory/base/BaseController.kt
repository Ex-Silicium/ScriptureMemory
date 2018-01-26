package com.exsilicium.scripturememory.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.exsilicium.scripturememory.di.Injector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseController : Controller() {
    private val disposables = CompositeDisposable()

    private var injected = false
    private var unbinder: Unbinder? = null

    protected abstract val layoutRes: Int

    protected abstract fun onViewBound(view: View)

    protected open fun subscriptions(): Array<Disposable> {
        return arrayOf()
    }

    final override fun onContextAvailable(context: Context) {
        /* Controller instances are retained across configuration changes, so this method can be called more than once.
         This makes sure we don't waste any time injecting more than once, though it wouldn't change functionality. */
        if (!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onContextAvailable(context)
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes, container, false)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(* subscriptions())
        return view
    }

    final override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        disposables.clear()
        unbinder?.let {
            it.unbind()
            unbinder = null
        }
    }
}