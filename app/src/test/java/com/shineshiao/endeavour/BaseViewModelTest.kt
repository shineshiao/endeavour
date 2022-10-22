package com.shineshiao.endeavour

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shineshiao.endeavour.base.BaseViewModel
import com.shineshiao.endeavour.data.DataManager
import com.shineshiao.endeavour.data.DataManagerImpl
import com.shineshiao.endeavour.data.local.database.Database
import com.shineshiao.endeavour.data.local.database.LocalDatabaseImpl
import com.shineshiao.endeavour.data.local.database.dao.ProductDao
import com.shineshiao.endeavour.data.local.prefs.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

/**
 * Created by thach.nguyen on 22,10,2022
 */
@RunWith(PowerMockRunner::class)
@ExperimentalCoroutinesApi
abstract class BaseViewModelTest<T : BaseViewModel> {

    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    protected lateinit var mViewModel: T
    protected lateinit var dataManager: DataManager
    private lateinit var localDatabase: Database

    @Mock
    lateinit var appPreferences: AppPreferences

    @Mock
    lateinit var productDao: ProductDao

    abstract fun setUp()
    abstract fun tearDown()

    @Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpBase() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        localDatabase = LocalDatabaseImpl(productDao)
        dataManager = DataManagerImpl(appPreferences, localDatabase)
        setUp()
    }

    @After
    fun tearDownBase() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        tearDown()
    }

    @Test
    fun test_addLoadingObservables() {
        Assert.assertEquals(getLoadingObservablesSize(), mViewModel.addLoadingObservables().size)
    }

    @Test
    fun test_addErrorObservables() {
        Assert.assertEquals(getErrorObservablesSize(), mViewModel.addErrorObservables().size)
    }

    abstract fun getLoadingObservablesSize(): Int
    abstract fun getErrorObservablesSize(): Int
}
