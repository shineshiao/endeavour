package com.shineshiao.endeavour.feature.favourite.repositories.impl

import com.shineshiao.endeavour.base.BaseApiResponse
import com.shineshiao.endeavour.data.DataManager
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.favourite.repositories.FavouriteRepository
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by thach.nguyen on 16,10,2022
 */
class FavouriteRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val dataManager: DataManager
) : FavouriteRepository, BaseApiResponse() {

    override suspend fun getFavouriteProducts(): Flow<List<ProductModel>> {
        return dataManager.getFavouriteProductsFlow()
    }

    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return dataManager.saveFavouriteProduct(productModel)
    }
}
