package com.exsilicium.scripturememory

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.exsilicium.scripturememory.memorypassage.MainActivity
import org.junit.Rule
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

}
