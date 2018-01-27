package com.exsilicium.scripturememory.passagedetail

import android.view.View
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.base.BaseController
import io.reactivex.disposables.Disposable

class PassageDetailController : BaseController() {
    override val layoutRes = R.layout.screen_passage_detail

    override fun onViewBound(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscriptions(): Array<Disposable> {
        return arrayOf()
    }
}
