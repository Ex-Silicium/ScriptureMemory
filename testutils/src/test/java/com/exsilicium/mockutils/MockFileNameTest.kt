package com.exsilicium.mockutils

import com.exsilicium.mockutils.MockFileName.getMockFileName
import com.exsilicium.mockutils.MockMemoryPassages.MOCK_REFERENCE_JOHN_3_16
import org.junit.Assert.assertEquals
import org.junit.Test

internal class MockFileNameTest {

    @Test fun testGetMockFilename() {
        assertEquals(
                "v3/passage/text/get_passage_detail_john_3_16_success.json",
                getMockFileName(MOCK_REFERENCE_JOHN_3_16)
        )
    }
}