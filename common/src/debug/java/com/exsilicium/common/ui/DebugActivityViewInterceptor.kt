package com.exsilicium.common.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.exsilicium.common.R
import com.exsilicium.common.R2
import com.exsilicium.common.settings.DebugPreferences
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class DebugActivityViewInterceptor @Inject constructor(
        private val debugPreferences: DebugPreferences,
        private val customizations: Set<@JvmSuppressWildcards DebugDrawerCustomization>
) : ActivityViewInterceptor {

    @BindView(R2.id.switch_mock_responses) lateinit var mockResponseSwitch: Switch
    @BindView(R2.id.ll_mock_responses_container) lateinit var mockResponsesContainer: View

    private var unbinder: Unbinder? = null

    private val disposables = CompositeDisposable()

    @SuppressLint("InflateParams")
    override fun setContentView(activity: Activity, layoutRes: Int) {
        val layoutInflater = LayoutInflater.from(activity)

        val debugLayout = layoutInflater.inflate(R.layout.debug_drawer, null)
        unbinder = ButterKnife.bind(this, debugLayout)
        initializePreferences()

        debugLayout.findViewById<ViewGroup>(R.id.activity_layout_container).addView(
                layoutInflater.inflate(layoutRes, null)
        )

        customizations.forEach { it.customize(debugLayout) }

        activity.setContentView(debugLayout)
    }

    override fun clear() {
        disposables.clear()
        unbinder?.let {
            it.unbind()
            unbinder = null
        }
    }

    private fun initializePreferences() {
        mockResponseSwitch.isChecked = debugPreferences.useMockPreferences

        disposables.addAll(
                RxCompoundButton.checkedChanges(mockResponseSwitch)
                        .doOnNext(RxView.visibility(mockResponsesContainer))
                        .subscribe { checked -> debugPreferences.useMockPreferences = checked }
        )
    }
}
