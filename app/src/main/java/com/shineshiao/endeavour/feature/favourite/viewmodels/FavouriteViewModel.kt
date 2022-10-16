package com.shineshiao.endeavour.feature.favourite.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shineshiao.endeavour.base.BaseViewModel
import com.shineshiao.endeavour.feature.favourite.interactors.FavouriteInteractor
import com.shineshiao.endeavour.model.ProductModel
import javax.inject.Inject

/**
 * Created by thach.nguyen on 16,10,2022
 */
class FavouriteViewModel @Inject constructor(val interactor: FavouriteInteractor) : BaseViewModel() {

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

    fun getFavouriteProducts() = callSuspendInScope {
        interactor.getFavouriteProducts().collect { productModel ->
            _productsLiveData.value = productModel
        }
    }
}
