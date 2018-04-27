package com.exsilicium.testutils

import com.exsilicium.mockutils.MockResourceLoader.MOCK_PATH
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
class JsonTestUtil @Inject internal constructor(
        private val testGson: Gson
) {

    fun <T : Any> loadMockJson(filename: String, klass: KClass<T>): T {
        val json = getFileString(MOCK_PATH + filename)
        return testGson.fromJson(json, klass.java)
    }

    private fun getFileString(path: String) =
            BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream(path)))
                    .use(BufferedReader::readText)

    companion object {
        private val jsonTestUtil: JsonTestUtil by lazy {
            JsonTestUtil(
                    GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create()
            )
        }

        fun <T : Any> loadMockJson(filename: String, klass: KClass<T>): T {
            return jsonTestUtil.loadMockJson(filename, klass)
        }
    }
}
