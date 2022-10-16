package com.shineshiao.endeavour.di.module

import com.shineshiao.endeavour.feature.favourite.interactors.FavouriteInteractor
import com.shineshiao.endeavour.feature.favourite.interactors.impl.FavouriteInteractorImpl
import com.shineshiao.endeavour.feature.homepage.interactors.HomeInteractor
import com.shineshiao.endeavour.feature.homepage.interactors.impl.HomeInteractorImpl
import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.feature.productdetail.interactors.impl.ProductDetailInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object InteractorModule {
    @Singleton
    @Provides
    fun provideHomeInteractor(interactor: HomeInteractorImpl): HomeInteractor {
        return interactor
    }

    @Singleton
    @Provides
    fun provideProductDetailInteractor(interactor: ProductDetailInteractorImpl): ProductDetailInteractor {
        return interactor
    }

    @Singleton
    @Provides
    fun provideFavouriteInteractor(interactor: FavouriteInteractorImpl): FavouriteInteractor {
        return interactor
    }
}
