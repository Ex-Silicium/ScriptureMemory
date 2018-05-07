package com.exsilicium.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.exsilicium.persistence.model.MemoryPassage

@Database(
        entities = [
            MemoryPassage::class
        ],
        version = 1
)
@TypeConverters(
        Converters::class
)
internal abstract class PassageDatabase : RoomDatabase() {

    abstract fun passageDao(): PassageDao
}