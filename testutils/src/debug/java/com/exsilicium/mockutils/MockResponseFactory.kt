package com.exsilicium.mockutils

import android.content.Context
import okhttp3.Request
import javax.inject.Inject

internal class MockResponseFactory @Inject constructor(
        private val context: Context
) {

    fun getMockResponse(request: Request) = MockResourceLoader.getResponse(
            context, request.method(), request.url().encodedPathSegments()
    )
}