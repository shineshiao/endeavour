package com.shineshiao.endeavour.feature.favourite.repositories

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 16,10,2022
 */
interface FavouriteRepository {
    suspend fun getFavouriteProducts(): Flow<List<ProductModel>>
}
