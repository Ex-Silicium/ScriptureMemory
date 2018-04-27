package com.exsilicium.testutils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

object TestUtils {

    val TEST_GSON = initializeGson()

    fun <T> loadJson(path: String, type: Type): T {
        val json = getFileString(path)
        return TEST_GSON.fromJson(json, type)
    }

    private fun getFileString(path: String) =
            BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream(path))).use(BufferedReader::readText)

    /**
     * This is the same thing as what's in NetworkModule.
     */
    private fun initializeGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
