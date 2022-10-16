package com.shineshiao.endeavour.feature.favourite.repositories

import com.shineshiao.endeavour.base.BaseNetworkResult
import com.shineshiao.endeavour.model.response.ProductResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 16,10,2022
 */
interface FavouriteRepository {
    suspend fun getFavouriteProducts(): Flow<BaseNetworkResult<ProductResponse>>
}
