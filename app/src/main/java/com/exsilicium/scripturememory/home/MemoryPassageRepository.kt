package com.exsilicium.scripturememory.home

import com.exsilicium.scripturememory.model.MemoryPassage
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
internal class MemoryPassageRepository @Inject constructor(
        private val requesterProvider: Provider<MemoryPassageRequester>
) {

    private val cachedMemoryPassages = mutableListOf<MemoryPassage>()

    fun getMemoryPassages(): Single<List<MemoryPassage>> {
        return Maybe.concat(cachedMemoryPassages(), persistedMemoryPassages())
                .firstOrError()
    }

    private fun cachedMemoryPassages(): Maybe<List<MemoryPassage>> {
        return Maybe.create { e ->
            if (cachedMemoryPassages.isEmpty()) e.onComplete()
            e.onSuccess(cachedMemoryPassages)
        }
    }

    private fun persistedMemoryPassages(): Maybe<List<MemoryPassage>> {
        return requesterProvider.get().getMemoryPassages()
                .doOnSuccess { passages ->
                    cachedMemoryPassages.clear()
                    cachedMemoryPassages.addAll(passages)
                }
                .toMaybe()
    }
}