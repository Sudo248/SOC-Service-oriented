package com.sudo248.soc_staff.ui.activity.main.fragment.home

import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<NavDirections>() {

    fun navigateToCategory() {
        navigator.navigateTo(HomeFragmentDirections.actionHomeFragmentToCategoryFragment())
    }

    fun navigateToProduct() {
        navigator.navigateTo(HomeFragmentDirections.actionHomeFragmentToProductFragment())
    }

    fun navigateToPromotion() {
        navigator.navigateTo(HomeFragmentDirections.actionHomeFragmentToPromotionFragment())
    }

    fun navigateToUser() {
        navigator.navigateTo(HomeFragmentDirections.actionHomeFragmentToUserFragment())
    }

}