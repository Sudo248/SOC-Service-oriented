package com.sudo248.soc.ui.activity.main.fragment.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc.data.api.auth.AuthService
import com.sudo248.soc.domain.entity.user.User
import com.sudo248.soc.domain.repository.AuthRepository
import com.sudo248.soc.domain.repository.UserRepository
import com.sudo248.soc.ui.activity.auth.AuthActivity
import com.sudo248.soc.ui.activity.main.MainViewModel
import com.sudo248.soc.ui.uimodel.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel<NavDirections>() {

    private val _user = MutableLiveData(User.empty())
    val user: LiveData<User> = _user

    var error: SingleEvent<String?> = SingleEvent(null)

    private lateinit var parentViewModel: MainViewModel

    init {
        getUserInfo()
    }

    fun setActivityViewModel(parentViewModel: MainViewModel) {
        this.parentViewModel = parentViewModel
    }

    private fun getUserInfo() = launch {
        userRepository.getUserInfo()
            .onSuccess {
                _user.postValue(it)
            }
            .onError{
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)
    }

    fun logout() = launch {
        authRepository.logout()
            .onSuccess {
                navigateToAuthActivity()
            }
            .onError {
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)
    }

    private fun navigateToAuthActivity() {
        parentViewModel.navigator().navigateOffAll(AuthActivity::class.createActionIntentDirections())
    }
}