package com.safagurdag.data.api.thedogapi

import com.safagurdag.data.api.ApiClient
import com.safagurdag.data.api.common.createHttpClient
import com.safagurdag.data.api.common.createMoshiConverterFactoryOrDefault
import com.safagurdag.data.extensions.addHeader
import com.safagurdag.data.extensions.addLogger
import com.safagurdag.data.extensions.addQueryParameters
import javax.inject.Inject

open class TheDogApiClient @Inject constructor() : ApiClient() {

    private val apiKey = "live_HIVhTepMoMiUQbOgB3a483mATo8r8N9pWCAaT20mLBmIMnChublpo9yRBmZwruGw"
    private val timestamp = System.currentTimeMillis().toString()

    private val defaultRequestsQueryParams = mapOf(
        "format" to "json",
        "mime_type" to "jpg",
        "has_breeds" to "true"
    )

    override val httpClient = createHttpClient()
        .addQueryParameters(defaultRequestsQueryParams)
        .addHeader("x-api-key", apiKey)
        .addLogger()
        .build()

    override val baseUrl = "https://api.thedogapi.com/"

    override val converterFactory = createMoshiConverterFactoryOrDefault()
}