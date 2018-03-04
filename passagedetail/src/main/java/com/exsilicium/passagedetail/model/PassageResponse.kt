package com.exsilicium.passagedetail.model

import com.google.gson.annotations.SerializedName

internal data class PassageResponse(
        val query: String,
        val canonical: String,
        val parsed: Set<IntArray>,
        @SerializedName("passage_meta") val passageMetadata: List<PassageMetadata>,
        val passages: List<String>
)
