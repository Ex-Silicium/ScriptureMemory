package com.exsilicium.common.ui

import com.exsilicium.scripture.shared.model.ScriptureReference

interface ActivityStarter {
    fun loadPassage(reference: ScriptureReference)
}