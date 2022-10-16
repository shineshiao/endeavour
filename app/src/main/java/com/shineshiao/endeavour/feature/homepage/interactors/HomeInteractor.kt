package com.shineshiao.endeavour.feature.homepage.interactors

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface HomeInteractor {
    suspend fun getProducts(): Flow<List<ProductModel>?>
    suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean>
}
