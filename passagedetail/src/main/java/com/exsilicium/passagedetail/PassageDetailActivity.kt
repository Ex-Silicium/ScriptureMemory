package com.exsilicium.passagedetail

import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.base.BaseController

class PassageDetailActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_home
    override val rootController: BaseController = PassageDetailController()
}