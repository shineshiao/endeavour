package com.shineshiao.endeavour.feature.homepage.repositories.impl

import com.shineshiao.endeavour.base.BaseApiResponse
import com.shineshiao.endeavour.base.BaseNetworkResult
import com.shineshiao.endeavour.data.DataManager
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.homepage.repositories.HomeRepository
import com.shineshiao.endeavour.model.ProductModel
import com.shineshiao.endeavour.model.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */
class HomeRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val dataManager: DataManager
) : HomeRepository, BaseApiResponse() {

    override suspend fun getProducts(): Flow<BaseNetworkResult<ProductResponse>> {
        return flow {
            emit(safeApiCall { productApi.getProducts() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return dataManager.saveFavouriteProduct(productModel)
    }

    override suspend fun saveProductsToDB(listProducts: List<ProductModel>): Flow<List<ProductModel>?> {
        return dataManager.saveProductsToDB(listProducts)
    }
}
