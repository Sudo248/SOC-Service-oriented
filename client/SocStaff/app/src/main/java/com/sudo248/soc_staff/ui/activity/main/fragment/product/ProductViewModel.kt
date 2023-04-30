package com.sudo248.soc_staff.ui.activity.main.fragment.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc_staff.domain.entity.discovery.CategoryInfo
import com.sudo248.soc_staff.domain.entity.discovery.Product
import com.sudo248.soc_staff.domain.entity.discovery.SupplierProduct
import com.sudo248.soc_staff.domain.entity.discovery.SupplierProductInfo
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import com.sudo248.soc_staff.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel<NavDirections>() {

    var categoryInfo = MutableLiveData<CategoryInfo?>(null)

    var currentProductInfo: SupplierProductInfo? = null

    private val _products = MutableLiveData<List<SupplierProductInfo>>()
    val products: LiveData<List<SupplierProductInfo>> = _products

    var error: SingleEvent<String?> = SingleEvent(null)

    init {
        getAllProduct()
    }

    fun getAllProduct() = launch {
        setState(UiState.LOADING)
        discoveryRepository.getSupplierProductInfo()
            .onSuccess {
                setState(UiState.SUCCESS)
                _products.postValue(it)
            }
            .onError {
                it.printStackTrace()
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

    fun postProduct(product: Product) = launch {
        setState(UiState.LOADING)
        var imageUrl = product.images[0]
        if (imageUrl.isNotEmpty()) {
            imageUrl = imageRepository.uploadImage(imageUrl).get()
        }
        discoveryRepository.postProduct(product.copy(images = listOf(imageUrl)))
            .onSuccess {
                getAllProduct()
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

    fun putProduct(product: Product) = launch {
        setState(UiState.LOADING)
        discoveryRepository.putProduct(product)
            .onSuccess {
                getAllProduct()
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }

}