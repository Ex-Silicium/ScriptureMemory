package com.exsilicium.scripturememory.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.exsilicium.scripturememory.R
import com.exsilicium.testutils.espresso.RecyclerViewItemCountAssertion

internal class PassageListRobot private constructor() {

    fun verifyLoading(showing: Boolean = true) = apply {
        onView(withId(R.id.progress_bar)).check(
                matches(withEffectiveVisibility(if (showing) Visibility.VISIBLE else Visibility.GONE))
        )
    }

    fun verifyPassageListContains(count: Int) {
        onView(withId(R.id.recycler_view)).check(RecyclerViewItemCountAssertion(count))
    }

    companion object {
        fun init() = PassageListRobot()
    }
}