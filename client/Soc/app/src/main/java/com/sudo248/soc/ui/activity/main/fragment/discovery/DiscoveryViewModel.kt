package com.sudo248.soc.ui.activity.main.fragment.discovery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc.domain.entity.discovery.Category
import com.sudo248.soc.domain.repository.DiscoveryRepository
import com.sudo248.soc.ui.activity.main.adapter.CategoryAdapter
import com.sudo248.soc.ui.activity.main.adapter.ProductAdapter
import com.sudo248.soc.ui.mapper.toCategoryUi
import com.sudo248.soc.ui.mapper.toListProductUi
import com.sudo248.soc.ui.uimodel.CategoryUiModel
import com.sudo248.soc.ui.uimodel.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:34 - 12/03/2023
 */
@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository
) : BaseViewModel<NavDirections>() {
    private val _userImage = MutableLiveData<String>()
    val userImage: LiveData<String> = _userImage

    private val _categoryName = MutableLiveData<String>("")
    val categoryName: LiveData<String> = _categoryName

    var error: SingleEvent<String?> = SingleEvent(null)

    val categoryAdapter = CategoryAdapter(::onCategoryItemClick)
    private val categories: MutableList<Category> = mutableListOf()

    val productAdapter = ProductAdapter(::onProductItemClick)

    init {
        launch {
            discoveryRepository.getAllCategory()
                .onSuccess {
                    categories.addAll(it)
                    categoryAdapter.submitList(getListCategoryUi())
                    productAdapter.submitList(getListProductFromCategory(categories.first()))
                }
                .onError {
                    error = SingleEvent(it.message)
                }.bindUiState(_uiState)
        }
    }

    private fun getListCategoryUi(selectedItemIndex: Int = 0): List<CategoryUiModel> {
        return categories.map { it.toCategoryUi() }
    }

    private fun getListProductUiByCategoryId(categoryId: String): List<ProductUiModel> {
        return getListProductFromCategory(categories.first { it.categoryId == categoryId })
    }

    private fun getListProductFromCategory(category: Category): List<ProductUiModel> {
        _categoryName.postValue(category.name)
        return category.products.flatMap { it.toListProductUi() }
    }

    private fun onCategoryItemClick(item: CategoryUiModel) {
        setState(UiState.LOADING)
        productAdapter.submitList(getListProductUiByCategoryId(item.categoryId))
        setState(UiState.SUCCESS)
    }

    private fun onProductItemClick(item: ProductUiModel) {
        navigator.navigateTo(DiscoveryFragmentDirections.actionDiscoveryFragmentToProductDetailFragment(item))
    }

    fun navigateToSearchView() {

    }
}