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

    @Query("SELECT * from products WHERE isFavourite = 1 ORDER BY id")
    fun getFavouriteProducts(): List<ProductModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProductsToDB(products: List<ProductModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteProduct(product: ProductModel)

    @Query("SELECT * FROM products WHERE isFavourite = 1 ORDER BY id")
    fun getFavouriteProductsFlow(): Flow<List<ProductModel>>
}
