package com.exsilicium.passagedetail.model

import com.google.gson.annotations.SerializedName

data class PassageResponse internal constructor(
        val query: String,
        val canonical: String,
        val parsed: Set<IntArray>,
        @SerializedName("passage_meta") val passageMetadata: List<PassageMetadata>,
        private val passages: List<String>
) {
    fun passage() = passages[0].trim()
}
