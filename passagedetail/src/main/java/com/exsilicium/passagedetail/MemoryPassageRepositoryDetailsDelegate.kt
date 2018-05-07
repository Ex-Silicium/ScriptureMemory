package com.exsilicium.passagedetail

import com.exsilicium.persistence.database.PassageDao
import com.exsilicium.persistence.model.MemoryPassage
import com.exsilicium.scripture.shared.model.ScriptureReference
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class MemoryPassageRepositoryDetailsDelegate @Inject constructor(
        private val passageDao: PassageDao
) {

    fun add(reference: ScriptureReference, text: String) {
        passageDao.save(MemoryPassage(reference, text))
    }

    fun contains(reference: ScriptureReference): Flowable<Boolean> {
        return passageDao.contains(reference)
                .subscribeOn(Schedulers.io())
    }

    fun getMemoryPassage(reference: ScriptureReference): Maybe<MemoryPassage> {
        return passageDao.getMemoryPassage(reference)
                .subscribeOn(Schedulers.io())
    }

    fun remove(reference: ScriptureReference) {
        passageDao.deletePassage(reference)
    }
}