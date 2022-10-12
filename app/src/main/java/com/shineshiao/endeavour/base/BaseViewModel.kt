package com.shineshiao.endeavour.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shineshiao.endeavour.error.ErrorWrapper
import com.shineshiao.endeavour.util.LiveDataUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by thach.nguyen on 13,10,2022
 */
abstract class BaseViewModel : ViewModel() {
    private val _loadingLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData
    private val _errorLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    lateinit var errorWrapper: ErrorWrapper

    internal fun init(
        errorWrapper: ErrorWrapper
    ) {
        this.errorWrapper = errorWrapper
    }

    abstract fun onDidBind()

    fun observeLoadingData(lifecycleOwner: LifecycleOwner) {
        if (addLoadingObservables().isNotEmpty()) {
            LiveDataUtil.zip(addLoadingObservables()).observe(
                lifecycleOwner,
                Observer {
                    for (ob in it) {
                        if (ob != null && ob) {
                            _loadingLiveData.postValue(true)
                            return@Observer
                        }
                    }
                    _loadingLiveData.postValue(false)
                }
            )
        }
    }

    fun observeErrorProcess(lifecycleOwner: LifecycleOwner) {
        LiveDataUtil.zip(addErrorObservables()).observe(
            lifecycleOwner,
            Observer { data ->
                data.forEach { ob ->
                    if (!ob.isNullOrEmpty()) {
                        _errorLiveData.postValue(ob)
                        addErrorObservables().forEach {
                            it.postValue(null)
                        }
                        return@Observer
                    }
                }
            }
        )
    }

    abstract fun addLoadingObservables(): MutableList<MutableLiveData<Boolean>>

    abstract fun addErrorObservables(): MutableList<MutableLiveData<String>>

    fun callSuspendInScope(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loadingLiveData.value = true
                block()
            } catch (error: Throwable) {
                _errorLiveData.value = error.message
            } finally {
                _loadingLiveData.value = false
            }
        }
    }
}
