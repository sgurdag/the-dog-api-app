package com.safagurdag.data.repository

import com.safagurdag.data.utils.NetworkUtil
import com.safagurdag.domain.common.FailureReason
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.DogApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

abstract class BaseRepository {

    @Inject
    lateinit var networkUtil: NetworkUtil

    suspend inline fun <reified T> invoke(
        response: Response<List<DogApiModel>>,
    ): Flow<ResultState<T>> {
        return flow {
            if (networkUtil.isNetworkAvailable()) {
                emit(ResultState.Loading())
                if (response.isSuccessful) {
                    emit(ResultState.success(response.body() as T))
                } else {
                    emit(ResultState.failure(FailureReason.Network404Error())) // Todo Server Error
                }
            } else {
                emit(ResultState.failure(FailureReason.Network404Error()))
            }
        }.flowOn(Dispatchers.IO)
    }
}