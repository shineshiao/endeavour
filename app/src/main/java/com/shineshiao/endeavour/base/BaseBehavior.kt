package com.shineshiao.endeavour.base

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface BaseBehavior {
    fun onBecomesVisible() {}
    fun onBecomesInvisible() {}
    fun onLoading(isLoading: Boolean) {}
    fun onError(error: String?) {}
    fun onKeyboardShowing(isShowing: Boolean) {}
    fun onViewLoaded() {}
    fun addViewListener() {}
    fun addDataObserve() {}
    fun onReSelectTab() {}
}
