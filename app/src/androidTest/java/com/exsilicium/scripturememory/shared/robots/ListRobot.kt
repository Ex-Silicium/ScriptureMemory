package com.exsilicium.scripturememory.shared.robots

import android.support.test.espresso.Espresso
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import com.exsilicium.scripturememory.R

internal class ListRobot {
    fun scrollTo(position: Int) {
        Espresso.onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }
}
