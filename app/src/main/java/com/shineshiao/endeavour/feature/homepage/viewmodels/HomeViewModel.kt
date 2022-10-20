package com.shineshiao.endeavour.feature.homepage.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shineshiao.endeavour.base.BaseViewModel
import com.shineshiao.endeavour.feature.homepage.interactors.HomeInteractor
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */

class HomeViewModel @Inject constructor(val interactor: HomeInteractor) : BaseViewModel() {

    private val _productsLiveData: MutableLiveData<List<ProductModel>?> by lazy { MutableLiveData() }
    val productsLiveData: LiveData<List<ProductModel>?>
        get() = _productsLiveData

    override fun onDidBind() {
    }

    override fun addLoadingObservables(): MutableList<MutableLiveData<Boolean>> {
        return arrayListOf()
    }

    override fun addErrorObservables(): MutableList<MutableLiveData<String>> {
        return arrayListOf()
    }

    fun getProducts() = callSuspendInScope {
        interactor.getProducts().collect { listProducts ->
            if (listProducts != null) {
                saveProductsToDB(listProducts)
            }
        }
    }

    fun saveProductsToDB(listProducts: List<ProductModel>) = callSuspendInScope {
        interactor.saveProductsToDB(listProducts).collect { result ->
            _productsLiveData.value = result
        }
    }

    fun toggleFavourite(data: ProductModel) = callSuspendInScope {
        val newProduct = data.copy(isFavourite = !data.isFavourite)
        interactor.toggleFavourite(newProduct).collect()
    }
}
