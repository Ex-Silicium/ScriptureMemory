package com.exsilicium.common.ui

import com.exsilicium.common.ui.GridHelper.Companion.calculateSpanCount
import org.junit.Assert.assertEquals
import org.junit.Test

class GridHelperTest {

    @Test
    fun calculateSpanCountWhenCalculatedSpanCountIsBelowMin() {
        assertEquals(5, calculateSpanCount(3, minSpanCount = 5))
    }

    @Test
    fun calculateSpanCountWhenCalculatedSpanCountExceedsMax() {
        assertEquals(5, calculateSpanCount(7, maxSpanCount = 5))
    }

    @Test
    fun calculateSpanCountWhenCalculatedSpanCountIsNotMultipleOfParameter() {
        assertEquals(10, calculateSpanCount(12, spanCountMultiple = 5))
    }

    @Test
    fun calculateSpanCount() {
        assertEquals(5, calculateSpanCount(5))
    }
}