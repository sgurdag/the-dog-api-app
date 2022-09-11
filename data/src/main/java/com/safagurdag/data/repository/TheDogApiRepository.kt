package com.safagurdag.data.repository

import com.safagurdag.data.api.thedogapi.TheDogApiService
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.DogApiModel
import com.safagurdag.domain.repository.ITheDogApiRepository
import com.safagurdag.domain.usecase.thedogapi.Params
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheDogApiRepository @Inject constructor(
    private val theDogApiService: TheDogApiService
) : BaseRepository(), ITheDogApiRepository {

    override suspend fun search(
        params: Params
    ): Flow<ResultState<List<DogApiModel>?>> =
        invoke(response = theDogApiService.search(
            size = params.size,
            page = params.page,
            limit = params.limit
        ))
}