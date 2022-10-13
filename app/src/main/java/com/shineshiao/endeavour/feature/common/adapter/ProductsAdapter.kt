package com.shineshiao.endeavour.feature.common.adapter

import android.util.SparseArray
import com.shineshiao.endeavour.base.BaseRecyclerAdapter
import com.shineshiao.endeavour.base.HolderType
import com.shineshiao.endeavour.feature.common.adapter.viewholder.ProductViewHolder

/**
 * Created by thach.nguyen on 13,10,2022
 */
class ProductsAdapter : BaseRecyclerAdapter<ProductsAdapter.ActionHolder>() {

    override fun addHoldersMap(holderTypeArray: SparseArray<Class<*>>) {
        holderTypeArray.put(
            TypeHolder.PRODUCT.viewTypeValue,
            ProductViewHolder::class.java
        )
    }

    enum class TypeHolder : HolderType {
        PRODUCT {
            override val viewTypeValue: Int
                get() = 1
        }
    }

    enum class ActionHolder {
        SELECTED_ITEM,
        TOGGLE_FAVOURITE
    }
}
