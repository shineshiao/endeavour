package com.shineshiao.endeavour.feature.homepage.viewmodels

import com.shineshiao.endeavour.BaseViewModelTest
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.homepage.interactors.HomeInteractor
import com.shineshiao.endeavour.feature.homepage.interactors.impl.HomeInteractorImpl
import com.shineshiao.endeavour.feature.homepage.repositories.HomeRepository
import com.shineshiao.endeavour.feature.homepage.repositories.impl.HomeRepositoryImpl
import com.shineshiao.endeavour.model.PriceModel
import com.shineshiao.endeavour.model.ProductModel
import com.shineshiao.endeavour.model.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response
import java.util.concurrent.CountDownLatch

/**
 * Created by thach.nguyen on 22,10,2022
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(
    ProductModel::class,
)
@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelTest<HomeViewModel>() {

    private lateinit var interactor: HomeInteractor
    private lateinit var repository: HomeRepository

    @Mock
    private lateinit var productApi: ProductApi

    override fun setUp() {
        repository = HomeRepositoryImpl(productApi, dataManager)
        interactor = HomeInteractorImpl(repository)
        mViewModel = HomeViewModel(interactor)
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
    fun testToggleFavouriteLiveDataLiveData() {
        Assert.assertNotNull(mViewModel.toggleFavouriteLiveData)
    }

    @Test
    fun testOnDidBind() {
        mViewModel.onDidBind()
    }

    @Test
    fun testGetProducts() {
        runBlocking {
            val latch = CountDownLatch(1)
            val job = launch(Dispatchers.Main) {
                // GIVEN
                val productResponse = ProductResponse(
                    listOf(
                        ProductModel(
                            id = "TestID",
                            price = listOf(
                                PriceModel(
                                    "test Price",
                                    10.0,
                                    false
                                )
                            )
                        )
                    )
                )
                Mockito.`when`(productApi.getProducts()).thenReturn(Response.success(productResponse))

                // WHEN
                mViewModel.getProducts()
                // THEN
                mViewModel.productsLiveData.observeForever { productModel ->
                    Assert.assertNotNull(productModel)
                    latch.countDown()
                }
            }
            latch.await()
            job.cancel()
        }
    }

    @Test
    fun testSaveProductsToDB() {
        runBlocking {
            val latch = CountDownLatch(1)
            val job = launch(Dispatchers.Main) {
                // WHEN
                mViewModel.saveProductsToDB(
                    listOf(
                        ProductModel(
                            id = "TestID",
                            price = listOf(
                                PriceModel(
                                    "test Price",
                                    10.0,
                                    false
                                )
                            )
                        )
                    )
                )
                // THEN
                mViewModel.productsLiveData.observeForever { listProduct ->
                    Assert.assertEquals(1, listProduct?.size)
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

    fun testGetInteractor() {}
}
