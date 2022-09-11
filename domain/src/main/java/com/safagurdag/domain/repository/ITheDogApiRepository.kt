package com.safagurdag.domain.repository

import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.DogApiModel
import com.safagurdag.domain.usecase.thedogapi.Params
import kotlinx.coroutines.flow.Flow

interface ITheDogApiRepository {

    suspend fun search(
        params: Params
    ): Flow<ResultState<List<DogApiModel>?>>
}