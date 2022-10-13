package com.shineshiao.endeavour.data.api

import com.shineshiao.endeavour.BuildConfig
import com.shineshiao.endeavour.model.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface ProductApi {
    @GET(BuildConfig.ENDPOINT_PRODUCT_LIST)
    suspend fun getProducts(): Response<ProductResponse>
}
