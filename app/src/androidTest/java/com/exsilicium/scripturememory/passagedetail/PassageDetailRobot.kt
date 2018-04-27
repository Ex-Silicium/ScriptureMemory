package com.exsilicium.scripturememory.passagedetail

import android.content.res.Resources
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import com.exsilicium.scripturememory.R
import com.exsilicium.testutils.espresso.TextColorMatcher
import org.hamcrest.core.StringContains.containsString

internal class PassageDetailRobot private constructor() {

    fun verifyLoading(showing: Boolean = true) = apply {
        onView(withId(R.id.progress_bar)).check(
                matches(withEffectiveVisibility(if (showing) Visibility.VISIBLE else Visibility.GONE))
        )
    }

    fun verifyPassageContentVisible(showing: Boolean = true) = apply {
        onView(withId(R.id.tv_passage_content)).check(
                matches(withEffectiveVisibility(if (showing) Visibility.VISIBLE else Visibility.GONE))
        )
    }

    fun verifyTextContains(text: String) = apply {
        onView(ViewMatchers.withId(R.id.tv_passage_content)).check(matches(withText(containsString(text))))
    }

    fun verifyPassageTextColor(color: Int, resources: Resources) = apply {
        onView(withId(R.id.tv_passage_content)).check(matches(TextColorMatcher.withTextColor(color, resources)))
    }

    companion object {
        fun init() = PassageDetailRobot()
    }
}