package com.shineshiao.endeavour.feature.favourite.viewmodels

import com.shineshiao.endeavour.BaseViewModelTest
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.favourite.interactors.FavouriteInteractor
import com.shineshiao.endeavour.feature.favourite.interactors.impl.FavouriteInteractorImpl
import com.shineshiao.endeavour.feature.favourite.repositories.FavouriteRepository
import com.shineshiao.endeavour.feature.favourite.repositories.impl.FavouriteRepositoryImpl
import com.shineshiao.endeavour.model.PriceModel
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch

/**
 * Created by thach.nguyen on 22,10,2022
 */
class FavouriteViewModelTest : BaseViewModelTest<FavouriteViewModel>() {

    private lateinit var interactor: FavouriteInteractor
    private lateinit var repository: FavouriteRepository

    @Mock
    private lateinit var productApi: ProductApi

    override fun setUp() {
        repository = FavouriteRepositoryImpl(productApi, dataManager)
        interactor = FavouriteInteractorImpl(repository)
        mViewModel = FavouriteViewModel(interactor)
    }

    override fun tearDown() {
    }

    override fun getLoadingObservablesSize(): Int {
        return 0
    }

    override fun getErrorObservablesSize(): Int {
        return 0
    }

    @Test
    fun testGetProductsLiveData() {
        Assert.assertNotNull(mViewModel.productsLiveData)
    }

    @Test
    fun testOnDidBind() {
        mViewModel.onDidBind()
    }

    @Test
    fun testGetFavouriteProducts() {
        runBlocking {
            val latch = CountDownLatch(1)
            val job = launch(Dispatchers.Main) {
                // GIVEN
                val flow = flow {
                    emit(
                        listOf(
                            ProductModel(
                                id = "TestID",
                                price = listOf(
                                    PriceModel(
                                        "test Price",
                                        10.0,
                                        false
                                    )
                                ),
                                isFavourite = true
                            )
                        )
                    )
                }
                Mockito.`when`(productDao.getFavouriteProductsFlow()).thenReturn(flow)
                // WHEN
                mViewModel.getFavouriteProducts()
                // THEN
                mViewModel.productsLiveData.observeForever { listProducts ->
                    Assert.assertEquals(1, listProducts?.size)
                    Assert.assertEquals("TestID", listProducts?.first()?.id)
                    Assert.assertEquals(true, listProducts?.first()?.isFavourite)
                    latch.countDown()
                }
            }
            latch.await()
            job.cancel()
        }
    }

    @Test
    fun testToggleFavourite() {
        runBlocking {
            val latch = CountDownLatch(1)
            val job = launch(Dispatchers.Main) {
                // GIVEN
                val productMock = ProductModel(
                    id = "TestID",
                    price = listOf(
                        PriceModel(
                            "test Price",
                            10.0,
                            false
                        )
                    ),
                    isFavourite = false
                )

                // WHEN
                mViewModel.toggleFavourite(productMock)

                // THEN
                mViewModel.toggleFavouriteLiveData.observeForever { isSuccess ->
                    Assert.assertEquals(true, isSuccess)
                    latch.countDown()
                }
            }
            latch.await()
            job.cancel()
        }
    }
}
