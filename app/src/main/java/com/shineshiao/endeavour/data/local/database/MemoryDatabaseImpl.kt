package com.shineshiao.endeavour.data.local.database

import com.shineshiao.endeavour.model.ProductModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.LinkedList
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */

@ActivityScoped
class MemoryDatabaseImpl @Inject constructor() : Database {
    private var products = LinkedList<ProductModel>()

    override suspend fun getProductsFlow(): Flow<List<ProductModel>> {
        return flow {
            emit(products)
        }
    }

    override suspend fun saveFavouriteProduct(product: ProductModel): Flow<Boolean> {
        return flow {
            products.add(product)
            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveProductsToDB(newListProducts: List<ProductModel>): Flow<List<ProductModel>?> {
        return flow {
            val listFavorites = products.filter { it.isFavourite }
            listFavorites.forEach { favouriteItem ->
                run {
                    val item = newListProducts.firstOrNull { it.id == favouriteItem.id }
                    item?.isFavourite = true
                }
            }
            products = newListProducts as LinkedList<ProductModel>
            emit(products)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFavouriteProductsFlow(): Flow<List<ProductModel>> {
        return flow {
            emit(products.filter { it.isFavourite })
        }
    }
}
