package com.exsilicium.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.exsilicium.common.dagger.Injector
import com.exsilicium.common.toolbar.ToolbarService
import com.exsilicium.common.utility.ResourceRetriever
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseController(
        args: Bundle? = null
) : Controller(args) {

    @Inject protected lateinit var resourceRetriever: ResourceRetriever
    @Inject lateinit var toolbarService: ToolbarService

    private val disposables = CompositeDisposable()

    private var injected = false
    private var unbinder: Unbinder? = null

    protected abstract val layoutRes: Int

    protected abstract fun onViewBound(view: View)

    protected open fun title(): String? = null

    protected open fun subscriptions(): Array<Disposable> = arrayOf()

    final override fun onContextAvailable(context: Context) {
        /* Controller instances are retained across configuration changes, so this method can be called more than once.
         This makes sure we don't waste any time injecting more than once, though it wouldn't change functionality. */
        if (!injected) {
            Injector.inject(this)
            injected = true
            onInjected()
        }
        super.onContextAvailable(context)
    }

    open fun onInjected() {
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes, container, false)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(* subscriptions())
        title()?.let { toolbarService.updateTitle(it) }
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