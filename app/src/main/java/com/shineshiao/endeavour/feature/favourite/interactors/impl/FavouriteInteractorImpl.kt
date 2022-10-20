package com.shineshiao.endeavour.feature.favourite.interactors.impl

import com.shineshiao.endeavour.feature.favourite.interactors.FavouriteInteractor
import com.shineshiao.endeavour.feature.favourite.repositories.FavouriteRepository
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by thach.nguyen on 16,10,2022
 */
@ExperimentalCoroutinesApi
class FavouriteInteractorImpl @Inject constructor(private val repository: FavouriteRepository) :
    FavouriteInteractor {

    override suspend fun getFavouriteProducts(): Flow<List<ProductModel>?> {
        return repository.getFavouriteProducts()
    }

    override suspend fun toggleFavourite(productModel: ProductModel): Flow<Boolean> {
        return repository.toggleFavourite(productModel)
    }
}
