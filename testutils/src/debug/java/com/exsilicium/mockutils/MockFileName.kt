package com.exsilicium.mockutils

import com.exsilicium.scripture.shared.model.ScriptureReference

/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
object MockFileName {

    private const val API_PREFIX = "v3/"
    private const val API_PASSAGE_TEXT = "passage/text/"
    private const val NAMING_CONVENTION_GET_PASSAGE_DETAIL_PREFIX = "get_passage_detail_"
    private const val FILE_TYPE_SUFFIX_JSON = ".json"

    fun getMockFileName(reference: ScriptureReference, success: Boolean = true): String {
        return API_PREFIX +
                API_PASSAGE_TEXT +
                NAMING_CONVENTION_GET_PASSAGE_DETAIL_PREFIX +
                reference.toString().toLowerCase().replace(' ', '_').replace(':', '_') +
                (if (success) "_success" else "_failure") +
                FILE_TYPE_SUFFIX_JSON
    }
}
