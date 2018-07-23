package com.exsilicium.scripturememory.home

import com.exsilicium.persistence.database.PassageDao
import com.exsilicium.persistence.model.MemoryPassage
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
internal class MemoryPassageRepository @Inject constructor(
        private val passageDaoProvider: Provider<PassageDao>
) {
    fun getMemoryPassages(): Flowable<List<MemoryPassage>> {
        return passageDaoProvider.get().getMemoryPassages()
                .subscribeOn(Schedulers.io())
    }
}