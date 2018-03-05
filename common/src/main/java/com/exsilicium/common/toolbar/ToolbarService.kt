package com.exsilicium.common.toolbar

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.exsilicium.common.R2
import com.exsilicium.common.extension.safeLet
import com.exsilicium.common.ui.ActivityLifecycleObserver
import com.exsilicium.daggerextension.annotation.ActivityScope
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class ToolbarService @Inject constructor(
) : ActivityLifecycleObserver() {

    @BindView(R2.id.toolbar) @JvmField var toolbar: Toolbar? = null

    private lateinit var unbinder: Unbinder

    private var title: String? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun setup() {
        unbinder = ButterKnife.bind(this, activity!!)

        safeLet(activity, toolbar) { activity, toolbar ->
            initializeTitle(activity, toolbar)
            activity.setSupportActionBar(toolbar)
            title?.let {
                toolbar.title = it
            }
        }
    }

    override fun onActivityDestroyed() {
        super.onActivityDestroyed()
        unbinder.unbind()
    }

    fun updateTitle(title: String) {
        this.title = title
        toolbar?.let { it.title = title }
    }

    companion object {
        private fun initializeTitle(activity: AppCompatActivity, toolbar: Toolbar) {
            try {
                val activityInfo = activity.packageManager.getActivityInfo(
                        activity.componentName,
                        PackageManager.GET_META_DATA
                )
                toolbar.title = activityInfo.loadLabel(activity.packageManager)
            } catch (e: PackageManager.NameNotFoundException) {
                Timber.e(e, "Error fetching Activity meta data")
            }
        }
    }
}
