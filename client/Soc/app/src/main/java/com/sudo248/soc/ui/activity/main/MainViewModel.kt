package com.sudo248.soc.ui.activity.main

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.soc.domain.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:15 - 23/02/2023
 */


@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationService: FusedLocationProviderClient,
) : BaseViewModel<IntentDirections>() {

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> = _imageUri

    var pickImageController: PickImageController? = null

    fun setImageUri(uri: Uri?) {
        _imageUri.postValue(uri)
    }

    fun pickImage() {
        pickImageController?.pickImage()
    }

    fun getCurrentLocation() = launch {
        Constants.location = _getCurrentLocation()
    }

    @SuppressLint("MissingPermission")
    private suspend fun _getCurrentLocation(): String = suspendCoroutine { continuation ->
        locationService.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnCompleteListener {
                it.result.run {
                    continuation.resume("$longitude,$latitude")
                }
            }

    }

}