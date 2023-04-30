package com.sudo248.soc_staff.ui.activity.main.fragment.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc_staff.domain.entity.discovery.Category
import com.sudo248.soc_staff.domain.entity.discovery.CategoryInfo
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import com.sudo248.soc_staff.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel<NavDirections>() {

    private val _categories = MutableLiveData<List<CategoryInfo>>()
    val categories: LiveData<List<CategoryInfo>> = _categories

    var error: SingleEvent<String?> = SingleEvent(null)

    init {
        getAllCategories()
    }

    fun getAllCategories() = launch {
        setState(UiState.LOADING)
        discoveryRepository.getCategoryInfo()
            .onSuccess {
                setState(UiState.SUCCESS)
                _categories.postValue(it)
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

    fun postCategory(category: Category) = launch {
        setState(UiState.LOADING)
        var imageUrl = category.imageUrl
        if (category.imageUrl.isNotEmpty()) {
            imageUrl = imageRepository.uploadImage(category.imageUrl).get()
        }
        discoveryRepository.postCategory(category.copy(imageUrl = imageUrl))
            .onSuccess {
                setState(UiState.SUCCESS)
                val categoryInfo = CategoryInfo(
                    it.categoryId,
                    it.name,
                    imageUrl
                )
                val list = _categories.value?.toMutableList() ?: mutableListOf()
                list.add(categoryInfo)
                _categories.postValue(list)
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

    fun putCategory(category: Category) = launch {
        setState(UiState.LOADING)
        discoveryRepository.putCategory(category)
            .onSuccess {
                setState(UiState.SUCCESS)
                val categoryInfo = CategoryInfo(
                    it.categoryId,
                    it.name,
                    it.imageUrl
                )
                val list = _categories.value?.toMutableList() ?: mutableListOf()
                list.add(categoryInfo)
                _categories.postValue(list)
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

}