package com.exsilicium.scripturememory.home

import com.exsilicium.mockutils.MockMemoryPassages.MOCK_PASSAGES
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import javax.inject.Provider

internal class MemoryPassageRepositoryTest {

    private val mockMemoryPassages = MOCK_PASSAGES
    private val mockPassageRequester = mock<MemoryPassageRequester>()

    private lateinit var repository: MemoryPassageRepository

    @Before fun setUp() {
        whenever(mockPassageRequester.getMemoryPassages()).thenReturn(Single.just(mockMemoryPassages))

        repository = MemoryPassageRepository(Provider { mockPassageRequester })
    }

    @Test fun getMemoryPassages() {
        assertRepoReturnsEntireMockList()

        // Modify the requester's response to prove the repository returns from the in-memory cache.
        val modifiedList = mockMemoryPassages.toMutableList()
        modifiedList.removeAt(0)
        whenever(mockPassageRequester.getMemoryPassages()).thenReturn(Single.just(modifiedList))

        assertRepoReturnsEntireMockList()
    }

    private fun assertRepoReturnsEntireMockList() {
        repository.getMemoryPassages().test().assertValue(mockMemoryPassages)
    }
}