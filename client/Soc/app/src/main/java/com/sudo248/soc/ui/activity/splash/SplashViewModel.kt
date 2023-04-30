package com.sudo248.soc.ui.activity.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.soc.domain.common.Constants
import com.sudo248.soc.domain.entity.user.Location
import com.sudo248.soc.domain.repository.AuthRepository
import com.sudo248.soc.ui.activity.auth.AuthActivity
import com.sudo248.soc.ui.activity.main.MainActivity
import com.sudo248.soc.ui.activity.payment.PaymentActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 08:12 - 06/03/2023
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AuthRepository,
    private val locationService: FusedLocationProviderClient,
) : BaseViewModel<IntentDirections>() {

    var viewController: ViewController? = null

    init {
        launch {
            delay(900)
            Constants.location = getCurrentLocation()
            accountRepository.tryGetToken()
                .onSuccess {
                    navigator.navigateOff(MainActivity::class.createActionIntentDirections())
                }
                .onError {
                    navigator.navigateOff(AuthActivity::class.createActionIntentDirections())
                }
        }

//            navigator.navigateOff(OtpActivity::class.createActionIntentDirections{
//                putExtra(Constants.Key.PHONE_NUMBER, "0989465270")
//            })
//            navigator.navigateOff(MainActivity::class.createActionIntentDirections())
//            navigator.navigateOff(PaymentActivity::class.createActionIntentDirections())
    }

    @SuppressLint("MissingPermission")
    private suspend fun getCurrentLocation(): String = suspendCoroutine { continuation ->
        if (viewController?.isGrantedLocationPermission() == true) {
            locationService.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener {
                    it.run {
                        continuation.resume("$longitude,$latitude")
                        Log.d("Sudoo", "getCurrentLocation: $longitude,$latitude")
                    }
                }
        } else {
            continuation.resume("")
        }
    }
}