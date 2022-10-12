package com.shineshiao.endeavour.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shineshiao.endeavour.error.ErrorWrapper
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */
abstract class BaseFragment<V : BaseViewModel> : Fragment(), BaseBehavior {

    @Inject
    lateinit var viewModel: V

    @Inject
    lateinit var errorWrapper: ErrorWrapper

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getTagFragment(): String

    override fun onError(error: String?) {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).onError(error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        observeLoadingProcess()
        observeErrorProcess()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewLoaded()
        addViewListener()
        addDataObserve()
    }

    fun addKeyboardCallback() {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).addKeyboardCallback(this)
        }
    }

    private fun observeLoadingProcess() {
        viewModel.loadingLiveData.observe(this, {
            onLoading(it)
        })
    }

    private fun observeErrorProcess() {
        viewModel.errorLiveData.observe(this, {
            onError(it)
        })
    }

    private fun setUpViewModel() {
        viewModel.init(errorWrapper)
        viewModel.onDidBind()
        viewModel.observeLoadingData(this)
        viewModel.observeErrorProcess(this)
    }
}
