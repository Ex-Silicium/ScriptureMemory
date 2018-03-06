package com.exsilicium.scripturememory.activity

import com.exsilicium.common.ui.ActivityStarter
import com.exsilicium.passagedetail.PassageDetailActivity
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.scripture.shared.model.ScriptureReference
import javax.inject.Inject

internal class DefaultActivityStarter @Inject constructor(
        private val screenNavigator: ScreenNavigator
) : ActivityStarter {
    override fun loadPassage(reference: ScriptureReference) {
        PassageDetailActivity.loadPassage(screenNavigator, reference)
    }
}