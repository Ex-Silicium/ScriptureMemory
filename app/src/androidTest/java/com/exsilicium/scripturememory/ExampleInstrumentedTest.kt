package com.exsilicium.scripturememory

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripturememory.shared.robots.ListRobot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule @JvmField val activityRule: TestRule = ActivityTestRule(MainActivity::class.java)

    @Test fun bookTitleShows() {
        onView(withText(Book.GENESIS.title)).check(matches(isDisplayed()))
        ListRobot().scrollTo(Book.values().size - 1)
        onView(withText(Book.REVELATION.title)).check(matches(isDisplayed()))
    }
}
