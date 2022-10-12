package com.shineshiao.endeavour.base

/**
 * Created by thach.nguyen on 13,10,2022
 */
interface ItemHolderListener<ActionHolder, Data> {
    fun onItemHolderClicked(actionHolder: ActionHolder, data: Data? = null, position: Int = -1)
}
