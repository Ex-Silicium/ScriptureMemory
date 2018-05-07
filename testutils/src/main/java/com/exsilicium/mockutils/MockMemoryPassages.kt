package com.exsilicium.mockutils

import com.exsilicium.persistence.model.MemoryPassage
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.Verse

@Suppress("MagicNumber")
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
object MockMemoryPassages {

    val MOCK_REFERENCE_JOHN_3_16 = ScriptureReference(Book.JOHN, Verse(3, 16))

    val MOCK_PASSAGE_JOHN_3_16 = MemoryPassage(MOCK_REFERENCE_JOHN_3_16, "For God so loved the world...")

    private val MOCK_PASSAGE_ROMANS_3_23 =
            MemoryPassage(ScriptureReference(Book.ROMANS, Verse(3, 23)), "For all have sinned...")

    val MOCK_PASSAGES = listOf(
            MOCK_PASSAGE_JOHN_3_16,
            MOCK_PASSAGE_ROMANS_3_23
    )
}
