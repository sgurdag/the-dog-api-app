package com.safagurdag.data.api.thedogapi

import com.safagurdag.domain.model.DogApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The dog api end-points contract.
 */
interface TheDogApiService {

    @GET("v1/images/search")
    suspend fun search(
        @Query("size") size: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<DogApiModel>>

}