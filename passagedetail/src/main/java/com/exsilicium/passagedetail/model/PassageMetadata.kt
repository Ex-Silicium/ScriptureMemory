package com.exsilicium.passagedetail.model

import com.google.gson.annotations.SerializedName

internal data class PassageMetadata(
        val canonical: String,
        @SerializedName("chapter_start") val chapterStart: List<Int>,
        @SerializedName("chapter_end") val chapterEnd: List<Int>,
        @SerializedName("prev_verse") val previousVerse: Int,
        @SerializedName("next_verse") val nextVerse: Int,
        @SerializedName("prev_chapter") val previousChapter: List<Int>,
        @SerializedName("next_chapter") val nextChapter: List<Int>
)
