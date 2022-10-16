/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shineshiao.endeavour.data.local.database

import com.shineshiao.endeavour.data.local.database.dao.ProductDao
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Data manager class that handles data manipulation between the database and the UI.
 */
class LocalDatabaseImpl @Inject constructor(
    private val productDao: ProductDao
) : Database {

    override suspend fun getProductsFlow(): Flow<List<ProductModel>> {
        return productDao.getProductsFlow()
    }

    override suspend fun saveFavouriteProduct(product: ProductModel): Flow<Boolean> {
        return flow {
            productDao.saveFavouriteProduct(product)
            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun removeFavouriteProduct(product: ProductModel): Flow<Boolean> {
        return flow {
            productDao.removeFavouriteProduct(product.id)
            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFavouriteProductsFlow(): Flow<List<ProductModel>> {
        return productDao.getFavouriteProductsFlow()
    }
}
