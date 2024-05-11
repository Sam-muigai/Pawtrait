package com.samkt.pawtrait.screen.catScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.samkt.pawtrait.data.CatApiService
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.repository.CatRepository
import com.samkt.pawtrait.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatListScreenModel : ScreenModel {
    private val repository =
        CatRepository(
            catApiService = CatApiService.getClient(),
        )

    private val _catListScreenState =
        MutableStateFlow<CatListScreenState>(CatListScreenState.Loading)
    val catListScreenState = _catListScreenState.asStateFlow()

    init {
        getCats()
    }

    private fun getCats() {
        screenModelScope.launch {
            repository.getAllCats().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _catListScreenState.value =
                            CatListScreenState.Error(result.message ?: "Unknown error occurred")
                    }

                    is Result.Success -> {
                        _catListScreenState.value =
                            CatListScreenState.Success(result.data ?: emptyList())
                    }
                }
            }
        }
    }
}

sealed interface CatListScreenState {
    data object Loading : CatListScreenState

    data class Success(val cats: List<CatResponse>) : CatListScreenState

    data class Error(val message: String) : CatListScreenState
}
