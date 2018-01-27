package com.exsilicium.scripturememory.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.base.BaseController
import com.exsilicium.scripturememory.picker.PassagePickerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MemoryPassageController : BaseController() {

    @Inject lateinit var presenter: MemoryPassagePresenter
    @Inject lateinit var viewModel: MemoryPassageViewModel

    @BindView(R.id.tv_message) lateinit var message: TextView
    @BindView(R.id.progress_bar) lateinit var progressBar: View
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.fab) lateinit var addActionButton: View

    override val layoutRes = R.layout.screen_memory_passages

    override fun subscriptions() = arrayOf(
            viewModel.loading()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { loading ->
                        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                        recyclerView.visibility = if (loading) View.GONE else View.VISIBLE
                    },
            viewModel.memoryPassagesUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { data ->
                        (recyclerView.adapter as MemoryPassageAdapter).setData(data)
                        val isDataEmpty = data.isEmpty()
                        recyclerView.visibility = if (isDataEmpty) View.GONE else View.VISIBLE
                        message.visibility = if (isDataEmpty) View.VISIBLE else View.GONE
                    },
            viewModel.showAddButton()
                    .map { show -> if (show) View.VISIBLE else View.GONE }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { visibility ->
                        addActionButton.visibility = visibility
                    }

    )

    override fun onViewBound(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        // todo Can I persist the adapter so I can save/restore scroll state?
        recyclerView.adapter = MemoryPassageAdapter(presenter)
    }

    @OnClick(R.id.fab) fun onClickFab() {
        PassagePickerHelper.addPassage(router)
    }
}
