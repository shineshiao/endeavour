package com.shineshiao.endeavour.feature.productdetail.interactors

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 15,10,2022
 */
interface ProductDetailInteractor {
    suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean>
}
