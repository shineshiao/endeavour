package com.shineshiao.endeavour.feature.homepage.repositories

import com.shineshiao.endeavour.base.BaseNetworkResult
import com.shineshiao.endeavour.model.response.ProductResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface HomeRepository {
    suspend fun getProducts(): Flow<BaseNetworkResult<ProductResponse>>
}
