package com.exsilicium.persistence.database

import android.arch.persistence.room.TypeConverter
import com.exsilicium.scripture.shared.ScriptureReferenceUtil
import com.exsilicium.scripture.shared.model.ScriptureReference
import java.util.Date

internal class Converters {
    @TypeConverter
    fun fromString(value: String) = ScriptureReferenceUtil.parse(value)

    @TypeConverter
    fun toString(reference: ScriptureReference) = "$reference"

    @TypeConverter
    fun fromTimestamp(value: Long) = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date) = date.time
}