package com.shineshiao.endeavour.feature.productdetail.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.base.BaseFragment
import com.shineshiao.endeavour.databinding.FragmentProductDetailBinding
import com.shineshiao.endeavour.feature.productdetail.viewmodels.ProductDetailViewModel
import com.shineshiao.endeavour.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by thach.nguyen on 15,10,2022
 */
@AndroidEntryPoint
class ProductDetailFragment(override val layoutId: Int = R.layout.fragment_product_detail) :
    BaseFragment<ProductDetailViewModel>() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    val args: ProductDetailFragmentArgs by navArgs()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCurrentProductData(args.currentProduct)
    }

    private fun loadCurrentProductData(product: ProductModel) {
        binding.tvTitle.text = product.title
        binding.imgProduct.load(product.imageURL)
        binding.ratingBar.rating = product.ratingCount.toFloat()
        // binding.txtPrice.text = product.price[0].value.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
