package com.sudo248.soc.ui.auth.fragment.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.soc.data.repository.AuthRepositoryImpl
import com.sudo248.soc.domain.entity.auth.Account
import com.sudo248.soc.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val authRepository: AuthRepository
) : BaseViewModel<NavDirections>() {

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun signIn() = launch {
        setState(UiState.LOADING)
        val account = Account(
            phoneNumber = _phoneNumber.value ?: "",
            password = _password.value ?: ""
        )
        authRepository.signIn(account).bindUiState(_uiState)
    }
}