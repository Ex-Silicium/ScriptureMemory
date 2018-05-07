package com.exsilicium.passagedetail

import com.exsilicium.mockutils.MockFileName.getMockFileName
import com.exsilicium.mockutils.MockMemoryPassages.MOCK_REFERENCE_JOHN_3_16
import com.exsilicium.passagedetail.model.PassageResponse
import com.exsilicium.passagedetail.service.PassageDetailRequester
import com.exsilicium.testutils.JsonTestUtil
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class PassageDetailPresenterTest {

    private val mockViewModel = mock<PassageDetailViewModel>()
    private val mockRequester = mock<PassageDetailRequester>()

    private val mockLoadingConsumer = mock<Consumer<Boolean>>()
    private val mockPassageUpdatedConsumer = mock<Consumer<String>>()
    private val mockErrorConsumer = mock<Consumer<Throwable>>()

    private lateinit var presenter: PassageDetailPresenter

    @Before fun setUp() {
        whenever(mockViewModel.loadingUpdated()).thenReturn(mockLoadingConsumer)
        whenever(mockViewModel.passageUpdated()).thenReturn(mockPassageUpdatedConsumer)
        whenever(mockViewModel.onError()).thenReturn(mockErrorConsumer)

        presenter = PassageDetailPresenter(mock(), mockViewModel, mockRequester)
    }

    @Test fun passageLoadSucceeded() {
        val successResponse = setupSuccess()
        presenter.loadPassageDetails()

        verify(mockPassageUpdatedConsumer).accept(successResponse.passage())
        verifyZeroInteractions(mockErrorConsumer)
        verifyLoadingStartedThenStopped()
    }

    @Test fun passageLoadFailed() {
        val failureResponse = setupFailure()
        presenter.loadPassageDetails()

        verify(mockErrorConsumer).accept(failureResponse)
        verifyZeroInteractions(mockPassageUpdatedConsumer)
        verifyLoadingStartedThenStopped()
    }

    private fun verifyLoadingStartedThenStopped() {
        inOrder(mockLoadingConsumer) {
            verify(mockLoadingConsumer).accept(true)
            verify(mockLoadingConsumer).accept(false)
        }
    }

    private fun setupSuccess() = JsonTestUtil.loadMockJson(
            getMockFileName(MOCK_REFERENCE_JOHN_3_16),
            PassageResponse::class
    ).apply {
        whenever(mockRequester.getDetails()).thenReturn(Single.just(passage()))
    }

    private fun setupFailure(): Throwable = IOException().apply {
        whenever(mockRequester.getDetails()).thenReturn(Single.error(this))
    }
}