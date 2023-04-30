package com.sudo248.soc_staff.ui.activity.main.fragment.search

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc_staff.databinding.FragmentSearchCategoryBinding
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.ui.activity.main.fragment.adater.CategoryAdapter
import com.sudo248.soc_staff.ui.activity.main.fragment.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchCategoryFragment : BaseFragment<FragmentSearchCategoryBinding, CategoryViewModel>() {
    override val viewModel: CategoryViewModel by activityViewModels()

    private var jobSearch: Job? = null

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter(canDelete = false){
            val data = bundleOf()
            data.putSerializable(Constants.Key.CATEGORY, it)
            back(Constants.Key.CATEGORY, data)
        }
    }

    override fun initView() {
        binding.rcvCategory.adapter = categoryAdapter
        binding.imgBack.setOnClickListener {
            back()
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                jobSearch?.cancel()
//                jobSearch = lifecycleScope.launch {
//                    delay(300)
                    categoryAdapter.search(newText ?: "")
//                }
                return true
            }
        })
    }

    override fun observer() {
        super.observer()
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it.reversed())
        }
    }
}