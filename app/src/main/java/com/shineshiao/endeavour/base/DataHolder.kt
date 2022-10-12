package com.shineshiao.endeavour.base

/**
 * Created by thach.nguyen on 13,10,2022
 */

interface DataHolder<T> {
    var holderType: HolderType
    var data: T
}
