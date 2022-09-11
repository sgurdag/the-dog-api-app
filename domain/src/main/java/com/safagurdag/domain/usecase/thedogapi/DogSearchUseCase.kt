package com.safagurdag.domain.usecase.thedogapi

import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.model.DogApiModel
import com.safagurdag.domain.repository.ITheDogApiRepository
import com.safagurdag.domain.usecase.UseCaseWithParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class DogSearchUiModel(
    var dogList: List<Dog>
) {
    data class Dog(
        val name: String,
        val imageUrl: String?,
        val temperament: String?,
        val life_span: String?,
        val bred_group: String?,
        val id: String,
    )
}

data class Params(
    val size: String = "med",
    val page: Int = 0,
    val limit: Int = 20,
)

class DogSearchUseCase @Inject constructor(
    private val repository: ITheDogApiRepository
) : UseCaseWithParams<DogSearchUiModel, Params>() {


    suspend operator fun invoke(params: Params) = run(params)

    override suspend fun run(params: Params): Flow<ResultState<DogSearchUiModel>> {
        return repository.search(params).map {
            when (it) {
                is ResultState.Success -> ResultState.success(getSearchUIModel(it.value))
                is ResultState.Failure -> ResultState.failure(it.failureReason)
                is ResultState.Loading -> ResultState.Loading()
            }
        }
    }

    private fun getSearchUIModel(dogList: List<DogApiModel>?): DogSearchUiModel {
        val dogsForUI = mutableListOf<DogSearchUiModel.Dog>()
        dogList?.forEach {
            if (it.breeds.isNotEmpty()) {
                val breed = it.breeds.first()
                dogsForUI.add(
                    DogSearchUiModel.Dog(
                        id = it.id,
                        name = breed.name,
                        imageUrl = it.url,
                        temperament = breed.temperament,
                        life_span = breed.life_span,
                        bred_group = breed.bred_for
                    )
                )
            }
        }
        return DogSearchUiModel(dogList = dogsForUI)
    }
}
