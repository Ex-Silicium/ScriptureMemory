package com.exsilicium.common.ui

import android.graphics.Point
import android.support.annotation.DimenRes
import android.support.annotation.VisibleForTesting
import com.exsilicium.common.extension.safeLet
import com.exsilicium.common.utility.ResourceRetriever
import com.exsilicium.daggerannotations.ActivityScope
import javax.inject.Inject

@ActivityScope
class GridHelper @Inject constructor(
        private val resourceRetriever: ResourceRetriever
) : ActivityLifecycleObserver() {

    private val screenWidth: Int
        get() {
            val outSize = Point()
            activity?.windowManager?.defaultDisplay?.getSize(outSize)
            return outSize.x
        }

    fun getSpanCount(
            @DimenRes targetWidthResId: Int,
            minSpanCount: Int = 1,
            maxSpanCount: Int? = null,
            spanCountMultiple: Int? = null
    ): Int {
        val calculatedSpanCount = screenWidth / resourceRetriever.getDimenPixels(targetWidthResId)
        return calculateSpanCount(calculatedSpanCount, minSpanCount, maxSpanCount, spanCountMultiple)
    }

    companion object {
        @VisibleForTesting fun calculateSpanCount(
                calculatedSpanCount: Int,
                minSpanCount: Int = 1,
                maxSpanCount: Int? = null,
                spanCountMultiple: Int? = null
        ): Int {
            safeLet(spanCountMultiple, maxSpanCount) { multiple, max ->
                require(multiple < max)
            }
            return if (calculatedSpanCount < minSpanCount) {
                minSpanCount
            } else if (maxSpanCount != null && calculatedSpanCount > maxSpanCount) {
                maxSpanCount
            } else {
                spanCountMultiple?.let {
                    var reducedSpanCount = calculatedSpanCount
                    while (reducedSpanCount % it != 0) {
                        reducedSpanCount--
                    }
                    reducedSpanCount
                } ?: calculatedSpanCount
            }
        }
    }
}