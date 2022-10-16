package com.shineshiao.endeavour.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by thach.nguyen on 13,10,2022
 */
@Dao
interface ProductDao {
    @Query("SELECT * from products ORDER BY id")
    fun getProductsFlow(): Flow<List<ProductModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteProduct(product: ProductModel)

    @Query("DELETE FROM products WHERE id =:productId")
    fun removeFavouriteProduct(productId: String)

    @Query("SELECT * FROM products ORDER BY id")
    fun getFavouriteProductsFlow(): Flow<List<ProductModel>>
}
