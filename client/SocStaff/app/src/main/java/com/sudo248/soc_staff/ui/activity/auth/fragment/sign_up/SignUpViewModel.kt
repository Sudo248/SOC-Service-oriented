package com.sudo248.soc_staff.ui.activity.auth.fragment.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onState
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.entity.discovery.Supplier
import com.sudo248.soc_staff.domain.repository.AuthRepository
import com.sudo248.soc_staff.domain.repository.DiscoveryRepository
import com.sudo248.soc_staff.ui.activity.auth.AuthViewModel
import com.sudo248.soc_staff.ui.activity.otp.OtpActivity
import com.sudo248.soc_staff.ui.mapper.toAccount
import com.sudo248.soc_staff.ui.uimodel.AccountUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:10 - 06/03/2023
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<NavDirections>() {
    private var parentViewModel: AuthViewModel? = null

    val accountUiModel: AccountUiModel = AccountUiModel()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val nameSupplier = MutableLiveData<String>()

    fun setParentViewModel(viewModel: AuthViewModel) {
        parentViewModel = viewModel
    }

    fun signUp() = launch {
        if (nameSupplier.value.isNullOrEmpty()) {
            _error.postValue("Tên nhà cung cấp không được để trống")
        } else {
            parentViewModel?.setState(UiState.LOADING)
            val account = accountUiModel.toAccount()
            authRepository.signUp(account).onState(
                onSuccess = {
                    SharedPreferenceUtils.putBoolean(Constants.Key.MUST_SETUP_LOCATION, true)
                    SharedPreferenceUtils.putString(Constants.Key.SUPPLIER_NAME, nameSupplier.value!!)
                    toOtpActivity(account.phoneNumber)
                },
                onError = {
                    _error.postValue(it.message)
                    accountUiModel.password.set("")
                    confirmPassword.postValue("")
                    parentViewModel?.setState(UiState.ERROR)
                }
            )
        }
    }

    private fun toOtpActivity(phoneNumber: String) {
        parentViewModel?.setState(UiState.SUCCESS)
        parentViewModel?.navigator()
            ?.navigateOff(OtpActivity::class.createActionIntentDirections {
                putExtra(Constants.Key.PHONE_NUMBER, phoneNumber)
            })
    }

    fun onConfirmPasswordTextChange(text: CharSequence) {
        if (text.toString() != accountUiModel.password.get()) {
            _error.postValue("Incorrect password")
        } else {
            _error.postValue("")
        }
    }
}