package com.shineshiao.endeavour.feature.productdetail.interactors.impl

import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */
@ExperimentalCoroutinesApi
class ProductDetailInteractorImpl @Inject constructor(private val repository: ProductDetailRepository) :
    ProductDetailInteractor {
    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return repository.toggleFavourite(productModel)
    }
}
