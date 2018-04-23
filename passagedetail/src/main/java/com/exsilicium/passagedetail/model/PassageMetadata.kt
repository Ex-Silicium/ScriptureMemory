package com.exsilicium.passagedetail.model

import com.google.gson.annotations.SerializedName

internal data class PassageMetadata(
        val canonical: String,
        val chapterStart: List<Int>,
        val chapterEnd: List<Int>,
        @SerializedName("prev_verse") val previousVerse: Int,
        val nextVerse: Int,
        @SerializedName("prev_chapter") val previousChapter: List<Int>,
        val nextChapter: List<Int>
)
