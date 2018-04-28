package com.exsilicium.passagedetail

import com.exsilicium.mockutils.MockFileName.getMockFileName
import com.exsilicium.mockutils.MockMemoryPassages.MOCK_REFERENCE_JOHN_3_16
import com.exsilicium.passagedetail.model.PassageResponse
import com.exsilicium.testutils.JsonTestUtil
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class PassageDetailViewModelTest {

    private lateinit var viewModel: PassageDetailViewModel

    @Before fun setUp() {
        viewModel = PassageDetailViewModel()
    }

    @Test fun loading() {
        val testObserver = viewModel.loading().test()

        viewModel.loadingUpdated().accept(true)
        viewModel.loadingUpdated().accept(false)

        testObserver.assertValues(true, false)
    }

    @Test fun passageUpdates() {
        val response = JsonTestUtil.loadMockJson(getMockFileName(MOCK_REFERENCE_JOHN_3_16), PassageResponse::class)

        viewModel.passageUpdated().accept(response.passage())

        viewModel.passageUpdates().test().assertValue(response.passage())
    }

    @Test fun errorUpdates() {
        val testObserver = viewModel.errorUpdates().test()

        viewModel.onError().accept(IOException())
        viewModel.passageUpdated().accept("For God so loved the world...")

        testObserver.assertValues(R.string.error_loading_passage, -1)
    }
}
