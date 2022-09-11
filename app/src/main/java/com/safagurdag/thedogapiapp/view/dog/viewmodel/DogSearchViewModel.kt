package com.safagurdag.thedogapiapp.view.dog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.usecase.thedogapi.DogSearchUiModel
import com.safagurdag.domain.usecase.thedogapi.DogSearchUseCase
import com.safagurdag.domain.usecase.thedogapi.Params
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogSearchViewModel @Inject constructor(private val searchUseCase: DogSearchUseCase) :
    ViewModel() {

    private val _searchState =
        MutableStateFlow<ResultState<DogSearchUiModel>>(ResultState.Loading())
    val searchState get() = _searchState

    suspend fun search() {
        _searchState.emit(ResultState.Loading())
        searchUseCase(Params()).collect {
            _searchState.tryEmit(
                when (it) {
                    is ResultState.Failure -> ResultState.failure(it.failureReason)
                    is ResultState.Loading -> ResultState.Loading()
                    is ResultState.Success -> ResultState.success(it.value)
                }
            )
        }
    }
}