package com.shineshiao.endeavour.feature.productdetail.repositories

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 15,10,2022
 */
interface ProductDetailRepository {
    suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean>
}
