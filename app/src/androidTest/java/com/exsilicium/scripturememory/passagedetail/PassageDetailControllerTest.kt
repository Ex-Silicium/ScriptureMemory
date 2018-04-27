package com.exsilicium.scripturememory.passagedetail

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.exsilicium.mockutils.MockMemoryPassages.MOCK_REFERENCE_JOHN_3_16
import com.exsilicium.passagedetail.PassageDetailActivity
import com.exsilicium.passagedetail.service.TestPassageService
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.application.MyApplication
import com.exsilicium.scripturememory.application.TestApplicationComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
internal class PassageDetailControllerTest {

    @Suppress("MemberVisibilityCanBePrivate")
    @Inject lateinit var testPassageService: TestPassageService

    @get:Rule private val testRule = ActivityTestRule(PassageDetailActivity::class.java, true, false)

    @Before fun setUp() {
        val application = InstrumentationRegistry.getTargetContext().applicationContext as MyApplication
        (application.component as TestApplicationComponent).inject(this)
    }

    @Test fun loadPassage() {
        launchActivity()

        PassageDetailRobot.init()
                .verifyLoading(false)
                .verifyPassageContentVisible()
                .verifyTextContains("For God so loved the world")
                .verifyPassageTextColor(R.color.text_grey, testRule.activity.resources)
    }

    @Test fun loadPassageError() {
        launchActivity(true)

        PassageDetailRobot.init()
                .verifyLoading(false)
                .verifyPassageContentVisible()
                .verifyTextContains("Error loading passage.")
                .verifyPassageTextColor(R.color.text_fail_red, testRule.activity.resources)
    }

    private fun launchActivity(withError: Boolean = false) {
        testPassageService.sendError = withError
        testRule.launchActivity(
                Intent().putExtras(
                        PassageDetailActivity.getExtrasBundle(MOCK_REFERENCE_JOHN_3_16)
                )
        )
    }
}