package com.exsilicium.scripturememory.memorypassage

import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.base.BaseActivity

class MainActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_main
    override val rootController = MemoryPassageController()
}
