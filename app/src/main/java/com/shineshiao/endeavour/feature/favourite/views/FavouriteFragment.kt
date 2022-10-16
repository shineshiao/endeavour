package com.shineshiao.endeavour.feature.favourite.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.base.BaseFragment
import com.shineshiao.endeavour.base.ItemHolderListener
import com.shineshiao.endeavour.databinding.FragmentFavouriteBinding
import com.shineshiao.endeavour.feature.common.adapter.ProductsAdapter
import com.shineshiao.endeavour.feature.favourite.viewmodels.FavouriteViewModel
import com.shineshiao.endeavour.model.ProductModel
import com.shineshiao.endeavour.util.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by thach.nguyen on 16,10,2022
 */
@AndroidEntryPoint
class FavouriteFragment(override val layoutId: Int = R.layout.fragment_favourite) :
    BaseFragment<FavouriteViewModel>() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    var productsAdapter: ProductsAdapter? = null

    companion object {
        const val tagFragment: String = "FavouriteFragment"
        fun newInstance(): FavouriteFragment {
            val fragment = FavouriteFragment()
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
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewLoaded() {
        super.onViewLoaded()
        initAdapters()
        viewModel.getFavouriteProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addDataObserve() {
        super.addDataObserve()
        viewModel.productsLiveData.observe(this, { listProducts ->
            if (!listProducts.isNullOrEmpty()) {
                productsAdapter?.itemList?.clear()
                productsAdapter?.addItems(listProducts, ProductsAdapter.TypeHolder.PRODUCT)
                productsAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun initAdapters() {
        productsAdapter = ProductsAdapter()
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_AROUND
        binding.rcvFeature.layoutManager = layoutManager
        binding.rcvFeature.addItemDecoration(SpacesItemDecoration(20))

        binding.rcvFeature.adapter = productsAdapter
        productsAdapter!!.listener =
            object : ItemHolderListener<ProductsAdapter.ActionHolder, Any> {
                override fun onItemHolderClicked(
                    actionHolder: ProductsAdapter.ActionHolder,
                    data: Any?,
                    position: Int
                ) {
                    if (actionHolder == ProductsAdapter.ActionHolder.SELECTED_ITEM) {
                        if (data is ProductModel) {
                            val action = FavouriteFragmentDirections.actionOpenProductDetail(currentProduct = data)
                            view?.findNavController()?.navigate(action)
                        }
                    }
                    if (actionHolder == ProductsAdapter.ActionHolder.TOGGLE_FAVOURITE) {
                        // todo: add to favourite list
                    }
                }
            }
    }
}
