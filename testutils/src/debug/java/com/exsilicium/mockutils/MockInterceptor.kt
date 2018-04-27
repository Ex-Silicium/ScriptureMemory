package com.exsilicium.mockutils

import com.exsilicium.common.settings.DebugPreferences
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class MockInterceptor @Inject internal constructor(
        private val mockResponseFactory: MockResponseFactory,
        private val debugPreferences: DebugPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (debugPreferences.useMockPreferences) {
            mockResponseFactory.getMockResponse(chain.request())?.let {
                return Response.Builder()
                        .message("")
                        .protocol(Protocol.HTTP_1_1)
                        .request(chain.request())
                        .code(CODE_SUCCESS)
                        .body(ResponseBody.create(MediaType.parse("text/json"), it))
                        .build()
            }
        }
        return chain.proceed(chain.request())
    }

    private companion object {
        const val CODE_SUCCESS = 200
    }
}