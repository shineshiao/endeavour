package com.shineshiao.endeavour.feature.homepage.interactors.impl

import com.shineshiao.endeavour.feature.homepage.interactors.HomeInteractor
import com.shineshiao.endeavour.feature.homepage.repositories.HomeRepository
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */
@ExperimentalCoroutinesApi
class HomeInteractorImpl @Inject constructor(private val repository: HomeRepository) :
    HomeInteractor {

    override suspend fun getProducts(): Flow<List<ProductModel>?> {
        return repository.getProducts().mapLatest { result ->
            result.data?.products
        }
    }

    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return repository.toggleFavourite(productModel)
    }
}
