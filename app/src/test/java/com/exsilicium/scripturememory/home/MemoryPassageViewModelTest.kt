package com.exsilicium.scripturememory.home

import com.exsilicium.mockutils.MockMemoryPassages.MOCK_PASSAGE_JOHN_3_16
import com.exsilicium.scripturememory.R
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class MemoryPassageViewModelTest {

    private lateinit var viewModel: MemoryPassageViewModel

    @Before fun setUp() {
        viewModel = MemoryPassageViewModel()
    }

    @Test fun loading() {
        val testObserver = viewModel.loading().test()

        viewModel.loadingUpdated().accept(true)
        viewModel.loadingUpdated().accept(false)

        testObserver.assertValues(true, false)
    }

    @Test fun memoryPassagesUpdates() {
        val mockMemoryPassages = listOf(MOCK_PASSAGE_JOHN_3_16)

        viewModel.memoryPassagesUpdated().accept(mockMemoryPassages)

        viewModel.memoryPassagesUpdates().test().assertValue(mockMemoryPassages)
    }

    @Test fun errorUpdates() {
        val testObserver = viewModel.errorUpdates().test()

        viewModel.onError().accept(IOException())
        viewModel.memoryPassagesUpdated().accept(emptyList())

        testObserver.assertValues(R.string.error_loading_memory_passages, -1)
    }
}