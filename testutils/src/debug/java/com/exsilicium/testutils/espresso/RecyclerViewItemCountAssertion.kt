package com.exsilicium.testutils.espresso

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.CoreMatchers.`is`

class RecyclerViewItemCountAssertion(
        private val expectedCount: Int
) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        assertThat(recyclerView.adapter.itemCount, `is`(expectedCount))
    }
}
