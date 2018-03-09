package com.exsilicium.passagedetail.service

import com.exsilicium.passagedetail.model.PassageResponse
import com.exsilicium.scripture.shared.model.ScriptureReference
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PassageService {

    @GET("passage/text/")
    @Suppress("LongParameterList")
    fun getPassage(
            @Query("q") reference: ScriptureReference,
            @Query("include-passage-references") includeReferences: Boolean = false,
            @Query("include-first-verse-numbers") includeFirstVerseNumbers: Boolean = false,
            @Query("include-verse-numbers") includeVerseNumbers: Boolean = false,
            @Query("include-footnotes") includeFootnotes: Boolean = false,
            @Query("include-footnote-body") includeFootnoteBody: Boolean = false,
            @Query("include-headings") includeHeadings: Boolean = false,
            @Query("include-selahs") includeSelahs: Boolean = false
    ): Single<PassageResponse>
}
