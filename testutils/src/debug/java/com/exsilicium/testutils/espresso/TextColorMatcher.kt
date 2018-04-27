package com.exsilicium.testutils.espresso

import android.content.res.Resources
import android.support.annotation.ColorRes
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Modified from [this gist](https://gist.github.com/Aracem/a596c40f9750f02f4e606dad170925e7).
 *
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
class TextColorMatcher private constructor(
        private val integerMatcher: Matcher<Int>
) : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun matchesSafely(textView: TextView) = integerMatcher.matches(textView.currentTextColor)

    override fun describeMismatch(item: Any, mismatchDescription: Description) {
        mismatchDescription.appendText("TextView with text color: ${(item as TextView).currentTextColor}, expected: ")
        integerMatcher.describeMismatch(item, mismatchDescription)
    }

    override fun describeTo(description: Description) {
        description.appendText("with text color ")
        integerMatcher.describeTo(description)
    }

    companion object {
        @Suppress("DEPRECATION") // This can be removed once minSdkVersion becomes 23 or higher.
        fun withTextColor(@ColorRes textColor: Int, resources: Resources): Matcher<View> =
                TextColorMatcher(Matchers.`is`(resources.getColor(textColor)))
    }
}