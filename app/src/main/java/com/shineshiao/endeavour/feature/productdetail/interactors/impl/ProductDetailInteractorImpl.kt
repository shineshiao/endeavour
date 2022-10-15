package com.shineshiao.endeavour.feature.productdetail.interactors.impl

import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */
@ExperimentalCoroutinesApi
class ProductDetailInteractorImpl @Inject constructor(private val repository: ProductDetailRepository) :
    ProductDetailInteractor
