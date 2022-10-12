package com.shineshiao.endeavour.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.shineshiao.endeavour.R

/**
 * Created by thach.nguyen on 13,10,2022
 */
abstract class BaseViewHolder<Data, ActionHolder> : RecyclerView.ViewHolder {
    var listener: ItemHolderListener<ActionHolder, Any>? = null
    var data: Data? = null

    // constructor to default
    constructor(parent: ViewGroup) : super(
        LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_default,
            parent,
            false
        )

    )

    // constructor to infused layoutID
    constructor(parent: ViewGroup, @LayoutRes layoutId: Int) : super(
        LayoutInflater.from(parent.context).inflate(
            layoutId,
            parent,
            false
        )
    )

    abstract fun onBind(data: Data)

    open fun onAttachToView() {
    }

    open fun onDetachFromView() {
    }
}
