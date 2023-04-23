package com.sudo248.soc.ui.activity.main.fragment.product_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc.domain.repository.DiscoveryRepository
import com.sudo248.soc.ui.uimodel.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 15:52 - 20/03/2023
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository
) : BaseViewModel<NavDirections>() {

    private val _supplierLocation =MutableLiveData("")
    val supplierLocation: LiveData<String> = _supplierLocation

    var error: SingleEvent<String?> = SingleEvent(null)
    // Khi khởi tạo view observer dữ liệu trong viewmodel nên sẽ hiển thị sai. Khi set lại value nhưng k notify nên k có dữ liệu
    var product: ProductUiModel = ProductUiModel()

    fun onClickLike() {
        product.isLike.set(!product.isLike.get()!!)
        product.isLike.notifyChange()
    }

    fun onBack() {
        navigator.back()
    }

}