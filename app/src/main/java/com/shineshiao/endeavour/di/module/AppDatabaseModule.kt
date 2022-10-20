package com.shineshiao.endeavour.di.module

import android.content.Context
import androidx.room.Room
import com.shineshiao.endeavour.data.local.database.AppDatabase
import com.shineshiao.endeavour.data.local.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by thach.nguyen on 13,10,2022
 */
@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "endeavour.db"
        ).build()
    }
}
