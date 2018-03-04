package com.exsilicium.scripturememory.home

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.passagepicker.PassagePickerActivity.Companion.addPassage
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.scripturememory.R
import com.jakewharton.rxbinding2.support.design.widget.visibility
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.view.visibility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class MemoryPassageController : BaseController() {

    @Inject lateinit var presenter: MemoryPassagePresenter
    @Inject lateinit var viewModel: MemoryPassageViewModel
    @Inject lateinit var screenNavigator: ScreenNavigator

    @BindView(R.id.tv_message) lateinit var message: TextView
    @BindView(R.id.progress_bar) lateinit var progressBar: View
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.fab) lateinit var addActionButton: FloatingActionButton

    override val layoutRes = R.layout.screen_memory_passages

    override fun subscriptions(): Array<Disposable> {
        val loadingObservable = viewModel.loading().observeOn(AndroidSchedulers.mainThread())
        val notLoadingObservable = loadingObservable.map { loading -> !loading }

        return arrayOf(
                RxView.clicks(addActionButton).subscribe { addPassage(screenNavigator) },
                loadingObservable.subscribe(progressBar.visibility()),
                notLoadingObservable.subscribe(recyclerView.visibility()),
                notLoadingObservable.subscribe(addActionButton.visibility()),
                viewModel.memoryPassagesUpdates()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { data ->
                            (recyclerView.adapter as MemoryPassageAdapter).setData(data)
                            val isDataEmpty = data.isEmpty()
                            recyclerView.visibility = if (isDataEmpty) View.GONE else View.VISIBLE
                            message.visibility = if (isDataEmpty) View.VISIBLE else View.GONE
                        }
        )
    }

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        // todo Can I persist the adapter so I can save/restore scroll state?
        recyclerView.adapter = MemoryPassageAdapter(presenter)
    }
}