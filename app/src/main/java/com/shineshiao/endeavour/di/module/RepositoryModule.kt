package com.shineshiao.endeavour.di.module

import com.shineshiao.endeavour.feature.homepage.repositories.HomeRepository
import com.shineshiao.endeavour.feature.homepage.repositories.impl.HomeRepositoryImpl
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import com.shineshiao.endeavour.feature.productdetail.repositories.impl.ProductDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(repository: HomeRepositoryImpl): HomeRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideProductDetailRepository(repository: ProductDetailRepositoryImpl): ProductDetailRepository {
        return repository
    }
}
