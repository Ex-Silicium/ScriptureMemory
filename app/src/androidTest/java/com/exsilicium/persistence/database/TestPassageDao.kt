package com.exsilicium.persistence.database

import com.exsilicium.mockutils.MockMemoryPassages.MOCK_PASSAGES
import com.exsilicium.persistence.model.MemoryPassage
import com.exsilicium.scripture.shared.model.ScriptureReference
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TestPassageDao @Inject constructor(
) : PassageDao {

    @Suppress("MemberVisibilityCanBePrivate")
    var containsReference = { _: ScriptureReference -> Pair<Boolean, MemoryPassage?>(false, null) }
    var sendErrorOnGetList = false

    override fun save(passage: MemoryPassage) {
    }

    override fun deletePassage(reference: ScriptureReference) {
    }

    override fun getMemoryPassages(): Flowable<List<MemoryPassage>> {
        return if (sendErrorOnGetList) {
            Flowable.error(IOException())
        } else {
            Flowable.just(MOCK_PASSAGES)
        }
    }

    override fun getMemoryPassage(reference: ScriptureReference): Maybe<MemoryPassage> {
        val memoryPassageInDatabase = containsReference(reference)
        return if (memoryPassageInDatabase.first) {
            Maybe.just(memoryPassageInDatabase.second)
        } else {
            Maybe.error(IOException())
        }
    }

    override fun contains(reference: ScriptureReference): Flowable<Boolean> =
            Flowable.just(containsReference(reference).first)
}