package com.sudo248.soc_staff.ui.activity.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.entity.discovery.Supplier
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import com.sudo248.soc_staff.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:15 - 23/02/2023
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val discoveryRepository: DiscoveryRepository
) : BaseViewModel<IntentDirections>() {

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> = _imageUri

    var pickImageController: PickImageController? = null

    init {
        SharedPreferenceUtils.getString(Constants.Key.SUPPLIER_NAME, "").let {
            if (it.isNotEmpty()) {
                registerSupplier(it)
            }
        }
    }

    fun registerSupplier(nameSupplier: String) = launch {
        discoveryRepository.postSupplier(Supplier(name = nameSupplier, avatar = "supplier_default.png"))
            .onSuccess {
                SharedPreferenceUtils.putString(Constants.Key.SUPPLIER_ID, it.supplierId)
                SharedPreferenceUtils.putString(Constants.Key.SUPPLIER_NAME, "")
            }
            .onError {
                it.printStackTrace()
            }
    }

    fun setImageUri(uri: Uri?) {
        _imageUri.postValue(uri)
    }

    fun pickImage() {
        pickImageController?.pickImage()
    }

    fun testUpload(context: Context, uri: Uri) = launch{
        imageRepository.uploadImage(context, uri)
            .onSuccess {
                Log.d("Sudoo", "testUpload: success")
            }
            .onError {
                it.printStackTrace()
            }
    }
}