package com.exsilicium.persistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.exsilicium.scripture.shared.model.ScriptureReference
import java.util.Date

@Entity(tableName = "memory_passage")
data class MemoryPassage(
        @PrimaryKey val reference: ScriptureReference,
        val text: String,
        val dateAdded: Date = Date(System.currentTimeMillis())
) {

    @Ignore val id = reference.hashCode().toLong()
}