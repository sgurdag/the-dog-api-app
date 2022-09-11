package com.safagurdag.domain.usecase

import com.safagurdag.domain.common.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Abstract class to represent a Use Case with params.
 *
 * ```
 * * Any Use Case that takes params in the application should
 *   implement this contract.
 */
abstract class UseCaseWithParams<out T, in Params> where T : Any {

    abstract suspend fun run(params: Params): Flow<ResultState<T>>

    suspend operator fun invoke(params: Params, onResult: (Flow<ResultState<T>>) -> Unit = {}) =
        onResult(run(params))
}