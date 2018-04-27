package com.exsilicium.mockutils

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException

internal object MockResourceLoader {

    internal const val MOCK_PATH = "mock/"

    fun getResponse(context: Context, method: String, endpointParts: List<String>): String? {
        require(endpointParts.isNotEmpty())

        val directory = endpointParts.filter { it.isNotEmpty() }.joinToString("/", MOCK_PATH)
        return try {
            val list = context.assets.list(directory)
            val matchingFilename = list.firstOrNull {
                it.contains(method.toLowerCase())
            }
            responseFromPath(context.assets, "$directory/$matchingFilename")
        } catch (e: IOException) {
            null
        }
    }

    private fun responseFromPath(assets: AssetManager, path: String): String {
        return assets.open(path).bufferedReader().use { it.readText() }
    }
}