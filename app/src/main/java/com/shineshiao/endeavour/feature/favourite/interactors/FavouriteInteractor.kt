package com.shineshiao.endeavour.feature.favourite.interactors

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 16,10,2022
 */
interface FavouriteInteractor {
    suspend fun getFavouriteProducts(): Flow<List<ProductModel>?>
    suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean>
}
