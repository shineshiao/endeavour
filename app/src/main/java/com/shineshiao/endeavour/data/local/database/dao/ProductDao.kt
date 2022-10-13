package com.shineshiao.endeavour.data.local.database.dao

import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 13,10,2022
 */
// @Dao
interface ProductDao {
//    @Query("SELECT * from products ORDER BY id")
    fun getProductsFlow(): Flow<List<ProductModel>>
}
