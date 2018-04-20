package com.exsilicium.passagepicker.book

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.exsilicium.common.ui.DebugDrawerCustomization
import com.exsilicium.passagepicker.R
import javax.inject.Inject

class BookListCustomization @Inject constructor(
) : DebugDrawerCustomization {

    override fun customize(debugLayout: View?) {
        debugLayout!!.findViewById<ViewGroup>(R.id.ll_other_debug_settings_container)
                .addView(TextView(debugLayout.context).apply {
                    text = "BOOK LIST" // todo keep playing with this
                })
    }
}