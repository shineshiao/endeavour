package com.shineshiao.endeavour.feature.common.adapter.viewholder

import android.animation.Animator
import android.view.ViewGroup
import coil.load
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

    private val favoriteStartFrame: Int = 25
    private val favoriteEndFrame: Int = 48
    private var isFavourite = false
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
        binding.imgAvatar.load(data.imageURL)
        binding.tvPrice.text = data.price[0].value.toString()
        binding.tvMessage.text = data.price[0].message

        binding.imgFavorites.setMaxFrame(favoriteEndFrame)
        binding.imgFavorites.setMinFrame(favoriteStartFrame)
        binding.imgFavorites.frame = favoriteStartFrame
        binding.imgFavorites.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                updateAnimation(true)
                listener?.onItemHolderClicked(
                    ProductsAdapter.ActionHolder.TOGGLE_FAVOURITE,
                    data
                )
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }
        })

        binding.imgFavorites.setOnClickListener {
            if (isFavourite) {
                listener?.onItemHolderClicked(
                    ProductsAdapter.ActionHolder.TOGGLE_FAVOURITE,
                    data
                )
                updateAnimation(false)
            } else {

                binding.imgFavorites.playAnimation()
            }
            isFavourite = !isFavourite
        }
    }

    private fun updateAnimation(isFavourite: Boolean) {
        val binding = ViewholderProductGridBinding.bind(itemView)
        if (isFavourite) {
            binding.imgFavorites.frame = favoriteEndFrame
        } else {
            binding.imgFavorites.frame = favoriteStartFrame
        }
    }
}
