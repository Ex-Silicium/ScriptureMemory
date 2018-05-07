package com.exsilicium.scripturememory.home

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.exsilicium.persistence.database.TestPassageDao
import com.exsilicium.scripturememory.application.MyApplication
import com.exsilicium.scripturememory.application.TestApplicationComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
internal class MemoryPassageControllerTest {

    @Suppress("MemberVisibilityCanBePrivate")
    @Inject lateinit var testpassageDao: TestPassageDao

    @get:Rule private val testRule = ActivityTestRule(HomeActivity::class.java, true, false)

    @Before fun setUp() {
        val application = InstrumentationRegistry.getTargetContext().applicationContext as MyApplication
        (application.component as TestApplicationComponent).inject(this)
    }

    @Test fun loadPassages() {
        launchActivity()

        PassageListRobot.init()
                .verifyLoading(false)
                .verifyPassageListContains(2)
    }

    @Test fun loadPassagesError() {
        launchActivity(true)

        PassageListRobot.init()
                .verifyLoading(false)
                .verifyPassageListContains(0)
    }

    private fun launchActivity(withError: Boolean = false) {
        testpassageDao.sendErrorOnGetList = withError
        testRule.launchActivity(null)
    }
}