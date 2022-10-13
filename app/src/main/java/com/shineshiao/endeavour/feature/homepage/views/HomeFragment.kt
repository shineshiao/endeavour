package com.shineshiao.endeavour.feature.homepage.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shineshiao.endeavour.R
import com.shineshiao.endeavour.base.BaseFragment
import com.shineshiao.endeavour.databinding.FragmentHomeBinding
import com.shineshiao.endeavour.feature.homepage.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by thach.nguyen on 13,10,2022
 */

@AndroidEntryPoint
class HomeFragment(override val layoutId: Int = R.layout.fragment_home) :
    BaseFragment<HomeViewModel>() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val tagFragment: String = "HomeFragment"
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewLoaded() {
        super.onViewLoaded()
        initAdapters()
        viewModel.getProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addDataObserve() {
        super.addDataObserve()
        viewModel.productsLiveData.observe(this, { listProducts ->
            if (!listProducts.isNullOrEmpty()) {
                // todo: add logic to load list here
            }
        })
    }

    private fun initAdapters() {
        // todo: add logic to init list here
    }
}
