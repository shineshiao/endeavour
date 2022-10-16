package com.shineshiao.endeavour.data.local.database

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface Database {
    suspend fun getProductsFlow(): Flow<List<ProductModel>>
    suspend fun saveFavouriteProduct(product: ProductModel): Flow<Boolean>
    suspend fun removeFavouriteProduct(product: ProductModel): Flow<Boolean>
    suspend fun getFavouriteProductsFlow(): Flow<List<ProductModel>>
}
