package com.exsilicium.scripturememory.home

import com.exsilicium.mockutils.MockMemoryPassages.MOCK_PASSAGES
import com.exsilicium.persistence.model.MemoryPassage
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class MemoryPassagePresenterTest {

    private val mockViewModel = mock<MemoryPassageViewModel>()
    private val mockRepository = mock<MemoryPassageRepository>()

    private val mockLoadingConsumer = mock<Consumer<Boolean>>()
    private val mockPassagesConsumer = mock<Consumer<List<MemoryPassage>>>()
    private val mockErrorConsumer = mock<Consumer<Throwable>>()

    private lateinit var presenter: MemoryPassagePresenter

    @Before fun setUp() {
        whenever(mockViewModel.loadingUpdated()).thenReturn(mockLoadingConsumer)
        whenever(mockViewModel.memoryPassagesUpdated()).thenReturn(mockPassagesConsumer)
        whenever(mockViewModel.onError()).thenReturn(mockErrorConsumer)

        presenter = MemoryPassagePresenter(mock(), mockViewModel, mockRepository, mock())
    }

    @Test fun loadPassagesSucceeded() {
        val successResponse = setupSuccess()
        presenter.loadMemoryPassages()

        verify(mockPassagesConsumer).accept(successResponse)
        verifyZeroInteractions(mockErrorConsumer)
        verifyLoadingStartedThenStopped()
    }

    @Test fun loadPassagesFailed() {
        val failureResponse = setupFailure()
        presenter.loadMemoryPassages()

        verify(mockErrorConsumer).accept(failureResponse)
        verifyZeroInteractions(mockPassagesConsumer)
        verifyLoadingStartedThenStopped()
    }

    private fun verifyLoadingStartedThenStopped() {
        inOrder(mockLoadingConsumer) {
            verify(mockLoadingConsumer).accept(true)
            verify(mockLoadingConsumer).accept(false)
        }
    }

    private fun setupSuccess() = MOCK_PASSAGES.apply {
        whenever(mockRepository.getMemoryPassages()).thenReturn(Flowable.just(this))
    }

    private fun setupFailure(): Throwable = IOException().apply {
        whenever(mockRepository.getMemoryPassages()).thenReturn(Flowable.error(this))
    }
}