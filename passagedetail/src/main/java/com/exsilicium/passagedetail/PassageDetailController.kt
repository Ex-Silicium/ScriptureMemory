package com.exsilicium.passagedetail

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.jakewharton.rxbinding2.view.visibility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject

internal class PassageDetailController : BaseController() {
    @Inject lateinit var scriptureReference: ScriptureReference
    @Suppress("unused")
    @Inject lateinit var presenter: PassageDetailPresenter
    @Inject lateinit var viewModel: PassageDetailViewModel

    @BindView(R2.id.progress_bar) lateinit var progressBar: View
    @BindView(R2.id.tv_passage_content) lateinit var passageText: TextView

    override val layoutRes = R.layout.screen_passage_detail

    override fun title() = scriptureReference.toString()

    override fun onViewBound(view: View) {}

    override fun subscriptions() = arrayOf(
            viewModel.loading()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(progressBar.visibility()),
            viewModel.passageUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            passageText::setText,
                            { t ->
                                passageText.text = t.message
                            }),
            viewModel.errorUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(handleError())
    )

    private fun handleError() = Consumer { errorResId: Int ->
        val hasError = errorResId != -1
        val colorRes = if (hasError) {
            R.color.text_fail_red
        } else {
            R.color.text_grey
        }
        passageText.setTextColor(resourceRetriever.getColor(colorRes))
        if (hasError) passageText.setText(errorResId)
    }

    internal companion object {
        const val KEY_BOOK_ORDINAL = "book"
        const val KEY_CHAPTER = "chapter"
        const val KEY_VERSE = "verse"
    }
}
