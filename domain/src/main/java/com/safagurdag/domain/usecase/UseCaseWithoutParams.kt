package com.safagurdag.domain.usecase

import com.safagurdag.domain.common.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Abstract class to represent a Use Case that takes no params.
 *
 * ```
 * * Any Use Case that takes no params in the application should
 *   implement this contract.
 */
abstract class UseCaseWithoutParams<out T> where T : Any {
    abstract suspend fun run(): Flow<ResultState<T>>
    suspend operator fun invoke(onResult: (Flow<ResultState<T>>) -> Unit = {}) = onResult(run())
}