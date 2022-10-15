package com.shineshiao.endeavour.feature.productdetail.views

import android.os.Bundle
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.base.BaseFragment
import com.shineshiao.endeavour.databinding.FragmentProductDetailBinding
import com.shineshiao.endeavour.feature.productdetail.viewmodels.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by thach.nguyen on 15,10,2022
 */
@AndroidEntryPoint
class ProductDetailFragment(override val layoutId: Int = R.layout.fragment_product_detail) :
    BaseFragment<ProductDetailViewModel>() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val tagFragment: String = "ProductDetailFragment"
        fun newInstance(): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getTagFragment(): String {
        return tagFragment
    }
}
