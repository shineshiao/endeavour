package com.shineshiao.endeavour.feature.common.adapter.viewholder

import android.view.ViewGroup
import coil.load
import coil.transform.RoundedCornersTransformation
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.base.BaseViewHolder
import com.shineshiao.endeavour.databinding.ViewholderProductGridBinding
import com.shineshiao.endeavour.feature.common.adapter.ProductsAdapter
import com.shineshiao.endeavour.model.ProductModel

/**
 * Created by thach.nguyen on 13,10,2022
 */
class ProductViewHolder(parent: ViewGroup) : BaseViewHolder<ProductModel,
    ProductsAdapter.ActionHolder>(parent, R.layout.viewholder_product_grid) {
    init {
        itemView.setOnClickListener {
            listener?.onItemHolderClicked(
                ProductsAdapter.ActionHolder.SELECTED_ITEM,
                data
            )
        }
    }

    override fun onBind(data: ProductModel) {
        val binding = ViewholderProductGridBinding.bind(itemView)
        binding.tvTitle.text = data.title
        binding.imgAvatar.load(data.imageURL) {
            transformations(RoundedCornersTransformation(8f))
        }
        binding.tvPrice.text = data.price[0].value.toString()
        binding.tvMessage.text = data.price[0].value.toString()

        binding.favouriteButton.setOnClickListener {
            listener?.onItemHolderClicked(
                ProductsAdapter.ActionHolder.TOGGLE_FAVOURITE,
                data
            )
        }
    }
}
