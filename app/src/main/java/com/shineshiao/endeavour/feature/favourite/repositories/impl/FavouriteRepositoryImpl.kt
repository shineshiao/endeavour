package com.shineshiao.endeavour.feature.favourite.repositories.impl

import com.shineshiao.endeavour.base.BaseApiResponse
import com.shineshiao.endeavour.base.BaseNetworkResult
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.favourite.repositories.FavouriteRepository
import com.shineshiao.endeavour.model.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by thach.nguyen on 16,10,2022
 */
class FavouriteRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : FavouriteRepository, BaseApiResponse() {

    override suspend fun getFavouriteProducts(): Flow<BaseNetworkResult<ProductResponse>> {
        return flow {
            emit(safeApiCall { productApi.getProducts() })
        }.flowOn(Dispatchers.IO)
    }
}
