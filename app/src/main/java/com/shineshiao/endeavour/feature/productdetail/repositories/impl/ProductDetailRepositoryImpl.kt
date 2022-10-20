package com.shineshiao.endeavour.feature.productdetail.repositories.impl

import com.shineshiao.endeavour.base.BaseApiResponse
import com.shineshiao.endeavour.data.DataManager
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */
class ProductDetailRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val dataManager: DataManager
) : ProductDetailRepository, BaseApiResponse() {
    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return dataManager.saveFavouriteProduct(productModel)
    }
}
