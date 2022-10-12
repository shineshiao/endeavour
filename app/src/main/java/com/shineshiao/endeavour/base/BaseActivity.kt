package com.shineshiao.endeavour.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.error.ErrorWrapper
import javax.inject.Inject

/**
 * Created by thach.nguyen on 13,10,2022
 */
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity(), BaseBehavior {

    @Inject
    lateinit var viewModel: V

    @Inject
    lateinit var errorWrapper: ErrorWrapper

    @get:LayoutRes
    protected abstract val layoutId: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        setUpViewModel()
        observeLoadingProcess()
        observeErrorProcess()
        onViewLoaded()
        addViewListener()
        addDataObserve()
    }

    private fun setUpViewModel() {
        viewModel.init(errorWrapper)
        viewModel.onDidBind()
        viewModel.observeLoadingData(this)
        viewModel.observeErrorProcess(this)
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

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun addKeyboardCallback(baseBehavior: BaseBehavior) {
        val rootView = findViewById<View>(R.id.container)
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener {
            val rec = Rect()
            rootView.getWindowVisibleDisplayFrame(rec)
            // finding screen height
            val screenHeight = rootView.rootView.height
            // finding keyboard height
            val keypadHeight = screenHeight - rec.bottom
            if (keypadHeight > screenHeight * 0.15) {
                baseBehavior.onKeyboardShowing(true)
            } else {
                baseBehavior.onKeyboardShowing(false)
            }
        }
    }
}
