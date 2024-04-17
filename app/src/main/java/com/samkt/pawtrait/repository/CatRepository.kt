package com.samkt.pawtrait.repository

import com.samkt.pawtrait.data.CatApiService
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatRepository(
    private val catApiService: CatApiService,
) {
    fun getAllCats(): Flow<Result<List<CatResponse>>> =
        flow {
            try {
                val cats = catApiService.getCats()
                emit(Result.Success(cats))
            } catch (e: Exception) {
                emit(Result.Error(e.localizedMessage))
            }
        }
}
