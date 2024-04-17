package com.samkt.pawtrait.screen.catDetailScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.samkt.pawtrait.data.CatApiService
import com.samkt.pawtrait.model.catDetails.CatDetailResponse
import com.samkt.pawtrait.repository.CatRepository
import com.samkt.pawtrait.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatDetailModel : ScreenModel {
    private val repository =
        CatRepository(
            catApiService = CatApiService.getClient(),
        )

    private val _catDetailScreenState = MutableStateFlow<CatDetailScreenState>(CatDetailScreenState.Loading)
    val catDetailScreenState = _catDetailScreenState.asStateFlow()

    fun getCatDetails(imageId: String) {
        screenModelScope.launch {
            repository.getCatDetails(imageId)
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _catDetailScreenState.value = CatDetailScreenState.Error(result.message ?: "Unknown error occurred")
                        }
                        is Result.Success -> {
                            _catDetailScreenState.value = CatDetailScreenState.Success(result.data)
                        }
                    }
                }
        }
    }
}

sealed interface CatDetailScreenState {
    data object Loading : CatDetailScreenState

    data class Success(val cats: CatDetailResponse?) : CatDetailScreenState

    data class Error(val message: String) : CatDetailScreenState
}
