package com.shineshiao.endeavour.data

import com.shineshiao.endeavour.data.local.database.Database
import com.shineshiao.endeavour.data.local.prefs.AppPreferences
import com.shineshiao.endeavour.di.module.BindLocalDatabase
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl @Inject constructor(
    private val mPreferences: AppPreferences,
    @BindLocalDatabase private val localDatabase: Database
) : DataManager {
    override suspend fun getProductsFlow(): Flow<List<ProductModel>> {
        return localDatabase.getProductsFlow()
    }

//    override suspend fun getDogsWithCategoryFlow(category: String): Flow<List<DogModel>> {
//        return localDatabase.getDogsWithCategoryFlow(category)
//    }
}
