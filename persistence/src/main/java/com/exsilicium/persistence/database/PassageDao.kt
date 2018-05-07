package com.exsilicium.persistence.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.exsilicium.persistence.model.MemoryPassage
import com.exsilicium.scripture.shared.model.ScriptureReference
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PassageDao {

    @Insert(onConflict = REPLACE)
    fun save(passage: MemoryPassage)

    @Query("DELETE FROM memory_passage WHERE reference = :reference")
    fun deletePassage(reference: ScriptureReference)

    @Query("SELECT * FROM memory_passage")
    fun getMemoryPassages(): Flowable<List<MemoryPassage>>

    @Query("SELECT * FROM memory_passage WHERE reference LIKE :reference")
    fun getMemoryPassage(reference: ScriptureReference): Maybe<MemoryPassage>

    @Query("SELECT count(*) FROM memory_passage WHERE reference LIKE :reference")
    fun contains(reference: ScriptureReference): Flowable<Boolean>
}