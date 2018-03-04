package com.exsilicium.scripturememory.home

import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity

internal class HomeActivity : BaseActivity() {
    override val isRoot = true
    override val layoutRes = R.layout.activity_home
    override val rootController = MemoryPassageController()
}
