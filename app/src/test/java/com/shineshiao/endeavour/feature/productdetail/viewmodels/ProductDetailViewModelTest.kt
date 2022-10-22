package com.shineshiao.endeavour.feature.productdetail.viewmodels

import com.shineshiao.endeavour.BaseViewModelTest
import com.shineshiao.endeavour.data.api.ProductApi
import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.feature.productdetail.interactors.impl.ProductDetailInteractorImpl
import com.shineshiao.endeavour.feature.productdetail.repositories.ProductDetailRepository
import com.shineshiao.endeavour.feature.productdetail.repositories.impl.ProductDetailRepositoryImpl
import com.shineshiao.endeavour.model.PriceModel
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import java.util.concurrent.CountDownLatch

/**
 * Created by thach.nguyen on 22,10,2022
 */
class ProductDetailViewModelTest : BaseViewModelTest<ProductDetailViewModel>() {
    private lateinit var interactor: ProductDetailInteractor
    private lateinit var repository: ProductDetailRepository

    @Mock
    private lateinit var productApi: ProductApi

    override fun setUp() {
        repository = ProductDetailRepositoryImpl(productApi, dataManager)
        interactor = ProductDetailInteractorImpl(repository)
        mViewModel = ProductDetailViewModel(interactor)
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
    fun testGetToggleFavouriteLiveData() {
        Assert.assertNotNull(mViewModel.toggleFavouriteLiveData)
    }

    @Test
    fun testOnDidBind() {
        mViewModel.onDidBind()
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
