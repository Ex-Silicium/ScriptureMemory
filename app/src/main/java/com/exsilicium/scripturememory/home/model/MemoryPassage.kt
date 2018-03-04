package com.exsilicium.scripturememory.home.model

import com.exsilicium.scripture.shared.model.ScriptureReference

data class MemoryPassage(
        val id: Int,
        val scriptureReference: ScriptureReference,
        val text: String
)