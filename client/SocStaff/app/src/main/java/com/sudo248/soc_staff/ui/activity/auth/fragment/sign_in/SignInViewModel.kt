package com.sudo248.soc_staff.ui.activity.auth.fragment.sign_in

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onState
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.repository.AuthRepository
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import com.sudo248.soc_staff.ui.activity.auth.AuthViewModel
import com.sudo248.soc_staff.ui.activity.main.MainActivity
import com.sudo248.soc_staff.ui.mapper.toAccount
import com.sudo248.soc_staff.ui.uimodel.AccountUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:06 - 06/03/2023
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val discoveryRepository: DiscoveryRepository
) : BaseViewModel<NavDirections>() {

    private var parentViewModel: AuthViewModel? = null

    val accountUiModel: AccountUiModel = AccountUiModel()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun setParentViewModel(viewModel: AuthViewModel) {
        parentViewModel = viewModel
    }

    fun signIn() = launch {
        parentViewModel?.setState(UiState.LOADING)
        delay(2000)
        authRepository.signIn(accountUiModel.toAccount()).onState(
            onSuccess = {
                authRepository.saveToken(it.token)
                discoveryRepository.getSupplier().get().run {
                    SharedPreferenceUtils.putString(Constants.Key.SUPPLIER_ID, supplierId)
                }
                parentViewModel?.setState(UiState.SUCCESS)
                parentViewModel?.navigator()
                    ?.navigateOff(MainActivity::class.createActionIntentDirections())
            },
            onError = {
                _error.postValue(it.message)
                accountUiModel.password.set("")
                parentViewModel?.setState(UiState.ERROR)
            }
        )
    }

    fun forgotPassword() {

    }
}