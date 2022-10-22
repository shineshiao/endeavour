package com.shineshiao.endeavour.feature.productdetail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shineshiao.endeavour.base.BaseViewModel
import com.shineshiao.endeavour.feature.productdetail.interactors.ProductDetailInteractor
import com.shineshiao.endeavour.model.ProductModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by thach.nguyen on 15,10,2022
 */

class ProductDetailViewModel @Inject constructor(val interactor: ProductDetailInteractor) : BaseViewModel() {
    private val _toggleFavouriteLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val toggleFavouriteLiveData: LiveData<Boolean>
        get() = _toggleFavouriteLiveData

    override fun onDidBind() {
    }

    override fun addLoadingObservables(): MutableList<MutableLiveData<Boolean>> {
        return arrayListOf()
    }

    override fun addErrorObservables(): MutableList<MutableLiveData<String>> {
        return arrayListOf()
    }

    fun toggleFavourite(data: ProductModel) = callSuspendInScope {
        val newProduct = data.copy(isFavourite = !data.isFavourite)
        interactor.toggleFavourite(newProduct).collect { isSuccess ->
            _toggleFavouriteLiveData.value = isSuccess
        }
    }
}
