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
    private lateinit var currentProduct: ProductModel
    val args: ProductDetailFragmentArgs by navArgs()

    companion object {
        private const val favoriteStartFrame: Int = 25
        private const val favoriteEndFrame: Int = 48
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
        currentProduct = args.currentProduct
        loadCurrentProductData()
    }

    override fun addDataObserve() {
        super.addDataObserve()
        binding.imgFavorites.setOnClickListener {
            viewModel.toggleFavourite(currentProduct)
            updateAnimation(!currentProduct.isFavourite)
        }
    }

    private fun updateAnimation(isFavourite: Boolean) {
        if (isFavourite) {
            binding.imgFavorites.frame = favoriteEndFrame
        } else {
            binding.imgFavorites.frame = favoriteStartFrame
        }
    }

    private fun loadCurrentProductData() {
        binding.tvTitle.text = currentProduct.title
        binding.imgProduct.load(currentProduct.imageURL)
        binding.ratingBar.rating = currentProduct.ratingCount.toFloat()
        updateAnimation(currentProduct.isFavourite)
        // binding.txtPrice.text = product.price[0].value.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
