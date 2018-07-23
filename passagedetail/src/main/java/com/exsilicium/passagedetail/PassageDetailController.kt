package com.exsilicium.passagedetail

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.exsilicium.common.base.BaseController
import com.exsilicium.common.ui.Constants.TIMEOUT_DEFAULT_MILLIS
import com.exsilicium.persistence.MemoryPassageRepositoryDetailsDelegate
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.jakewharton.rxbinding2.view.visibility
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

internal class PassageDetailController : BaseController() {
    @Inject lateinit var passageRepositoryProvider: Provider<MemoryPassageRepositoryDetailsDelegate>
    @Inject lateinit var scriptureReference: ScriptureReference
    @Inject lateinit var presenter: PassageDetailPresenter
    @Inject lateinit var viewModel: PassageDetailViewModel

    @BindView(R2.id.button_my_passages) lateinit var inMyPassagesButton: TextView
    @BindView(R2.id.progress_bar) lateinit var progressBar: View
    @BindView(R2.id.tv_passage_content) lateinit var passageText: TextView

    override val layoutRes = R.layout.screen_passage_detail

    override fun title() = "$scriptureReference"

    override fun onViewBound(view: View) {}

    override fun subscriptions() = arrayOf(
            passageRepositoryProvider.get().contains(scriptureReference)
                    .subscribe(viewModel.inMyPassagesUpdate()),
            viewModel.loading()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(progressBar.visibility()),
            viewModel.passageUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { inMyPassagesButton.visibility = View.VISIBLE }
                    .subscribe(
                            passageText::setText,
                            { passageText.text = it.message }),
            viewModel.inMyPassagesUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { if (it) R.string.remove_from_my_passages else R.string.add_to_my_passages }
                    .subscribe(RxTextView.textRes(inMyPassagesButton)),
            viewModel.inMyPassagesUpdates()
                    .take(1)
                    .subscribe {
                        if (it) {
                            addDisposable(
                                    passageRepositoryProvider.get().getMemoryPassage(scriptureReference)
                                            .map { it.text }
                                            .subscribe(viewModel.passageUpdated())
                            )
                        } else {
                            presenter.loadPassageDetails()
                        }
                    },
            clicks(inMyPassagesButton)
                    .debounce(TIMEOUT_DEFAULT_MILLIS, TimeUnit.MILLISECONDS)
                    .flatMap { viewModel.inMyPassagesUpdates().take(1) }
                    .observeOn(Schedulers.io())
                    .subscribe {
                        if (it) {
                            passageRepositoryProvider.get().remove(scriptureReference)
                        } else {
                            passageRepositoryProvider.get().add(scriptureReference, passageText.text.toString())
                        }
                    },
            viewModel.errorUpdates()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(handleError())
    )

    private fun handleError() = Consumer { errorResId: Int ->
        val hasError = errorResId != -1
        val colorRes = if (hasError) {
            R.color.fail_red
        } else {
            R.color.text_passage_detail
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
