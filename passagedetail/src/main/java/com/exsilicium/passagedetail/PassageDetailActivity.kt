package com.exsilicium.passagedetail

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.base.BaseController
import com.exsilicium.passagedetail.PassageDetailController.Companion.KEY_BOOK_ORDINAL
import com.exsilicium.passagedetail.PassageDetailController.Companion.KEY_CHAPTER
import com.exsilicium.passagedetail.PassageDetailController.Companion.KEY_VERSE
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.screennavigator.ScreenTransaction
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.VerseRanges

class PassageDetailActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_home
    override val rootController: BaseController = PassageDetailController()

    companion object {
        fun loadPassage(
                screenNavigator: ScreenNavigator,
                reference: ScriptureReference
        ) {
            screenNavigator.push(
                    ScreenTransaction.forActivity(
                            PassageDetailActivity::class,
                            getExtrasBundle(reference)
                    )
            )
        }

        @VisibleForTesting fun getExtrasBundle(reference: ScriptureReference): Bundle {
            return Bundle().apply {
                putInt(KEY_BOOK_ORDINAL, reference.book.ordinal)
                reference.location.let {
                    if (it is VerseRanges) {
                        val verseRangeStart = it.verseRanges.first().start
                        putInt(KEY_CHAPTER, verseRangeStart.chapter)
                        putInt(KEY_VERSE, verseRangeStart.verseNumber)
                    } else {
                        // Allow Scripture Reference that doesn't have a Set<VerseRange>.
                    }
                }
            }
        }
    }
}