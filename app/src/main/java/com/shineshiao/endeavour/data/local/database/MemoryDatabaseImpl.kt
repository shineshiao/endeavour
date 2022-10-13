package com.shineshiao.endeavour.data.local.database

import com.shineshiao.endeavour.model.ProductModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.LinkedList
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */

@ActivityScoped
class MemoryDatabaseImpl @Inject constructor() : Database {
    private val products = LinkedList<ProductModel>()

    override suspend fun getProductsFlow(): Flow<List<ProductModel>> {
        return flow {
            emit(products)
        }
    }
}
