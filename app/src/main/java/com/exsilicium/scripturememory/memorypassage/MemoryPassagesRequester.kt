package com.exsilicium.scripturememory.memorypassage

import com.exsilicium.scripturememory.model.MemoryPassage
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MemoryPassagesRequester @Inject constructor(

) {
    fun getMemoryPassages(): Single<List<MemoryPassage>> {
        return Single.just(emptyList<MemoryPassage>())
                .delay(1500, TimeUnit.MILLISECONDS)
    }
}