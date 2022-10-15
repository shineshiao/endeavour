package com.shineshiao.endeavour.feature.productdetail.repositories.impl

import com.shineshiao.endeavour.base.BaseApiResponse
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */
class ProductDetailRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductDetailRepository, BaseApiResponse()
