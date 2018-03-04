package com.exsilicium.passagedetail

import android.view.View
import com.exsilicium.common.base.BaseController
import io.reactivex.disposables.Disposable

internal class PassageDetailController : BaseController() {
    override val layoutRes = R.layout.screen_passage_detail

    override fun onViewBound(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscriptions(): Array<Disposable> {
        return arrayOf()
    }
}
