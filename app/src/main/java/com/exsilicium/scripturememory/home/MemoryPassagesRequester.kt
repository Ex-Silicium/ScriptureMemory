package com.exsilicium.scripturememory.home

import com.exsilicium.scripturememory.home.model.MemoryPassage
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class MemoryPassagesRequester @Inject constructor(
) {

    fun getMemoryPassages(): Single<List<MemoryPassage>> {
        return Single.just(emptyList<MemoryPassage>())
                .delay(1500, TimeUnit.MILLISECONDS)
    }
}
