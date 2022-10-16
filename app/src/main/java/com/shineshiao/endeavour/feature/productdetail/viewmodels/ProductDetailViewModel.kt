package com.shineshiao.endeavour.feature.productdetail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shineshiao.endeavour.base.BaseViewModel
import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.model.ProductModel
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */

class ProductDetailViewModel @Inject constructor(val interactor: ProductDetailInteractor) : BaseViewModel() {

    private val _currentProductLiveData: MutableLiveData<ProductModel?> by lazy { MutableLiveData() }
    val currentProductLiveData: LiveData<ProductModel?>
        get() = _currentProductLiveData

    override fun onDidBind() {
    }

    override fun addLoadingObservables(): MutableList<MutableLiveData<Boolean>> {
        return arrayListOf()
    }

    override fun addErrorObservables(): MutableList<MutableLiveData<String>> {
        return arrayListOf()
    }
}
