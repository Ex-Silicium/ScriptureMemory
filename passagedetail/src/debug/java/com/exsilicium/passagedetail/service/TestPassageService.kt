package com.exsilicium.passagedetail.service

import com.exsilicium.mockutils.MockFileName
import com.exsilicium.passagedetail.model.PassageResponse
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.testutils.JsonTestUtil
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
class TestPassageService @Inject constructor(
        private val jsonTestUtil: JsonTestUtil
) : PassageService {

    var sendError: Boolean = false

    override fun getPassage(
            reference: ScriptureReference,
            includeReferences: Boolean,
            includeFirstVerseNumbers: Boolean,
            includeVerseNumbers: Boolean,
            includeFootnotes: Boolean,
            includeFootnoteBody: Boolean,
            includeHeadings: Boolean,
            includeSelahs: Boolean
    ): Single<PassageResponse> {
        return if (sendError) {
            Single.error(IOException())
        } else {
            Single.just(jsonTestUtil.loadMockJson(MockFileName.PASSAGE_JOHN_3_16_SUCCESS, PassageResponse::class))
        }
    }
}