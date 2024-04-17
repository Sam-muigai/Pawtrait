package com.samkt.pawtrait.repository

import com.samkt.pawtrait.data.CatApiService
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.model.catDetails.CatDetailResponse
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

    fun getCatDetails(imageId: String): Flow<Result<CatDetailResponse>> =
        flow {
            try {
                val catDetail = catApiService.getCatDetails(imageId)
                emit(Result.Success(catDetail))
            } catch (e: Exception) {
                emit(Result.Error(e.localizedMessage))
            }
        }
}
