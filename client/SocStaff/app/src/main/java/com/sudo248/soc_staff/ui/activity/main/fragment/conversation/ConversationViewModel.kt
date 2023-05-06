package com.sudo248.soc_staff.ui.activity.main.fragment.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc_staff.domain.entity.chat.Chat
import com.sudo248.soc_staff.domain.entity.chat.ConversationInfo
import com.sudo248.soc_staff.domain.repository.ChatRepository
import com.sudo248.soc_staff.ui.service.FirebaseMessageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : BaseViewModel<NavDirections>() {

    private val _conversation = MutableLiveData<List<ConversationInfo>>()
    val conversation: LiveData<List<ConversationInfo>> = _conversation

    var error: SingleEvent<String?> = SingleEvent(null)

    private var shouldShowLoading = true

    private val observerLiveData = Observer<Boolean> {
        if (it) getAllConversation()
    }

    init {
        getAllConversation()
        launch {
            FirebaseMessageService.conversationFlow.collect {
                if (it) getAllConversation()
            }
        }
    }

    fun getAllConversation() = launch {
        if (shouldShowLoading) emitState(UiState.LOADING)
        chatRepository.getAllConversationInfo()
            .onSuccess {
                shouldShowLoading = false
                _conversation.postValue(it)
                emitState(UiState.SUCCESS)
            }
            .onError {
                error = SingleEvent(it.message)
                emitState(UiState.ERROR)
            }
    }

    fun openChat(conversationInfo: ConversationInfo) = launch {
        navigator.navigateTo(ConversationFragmentDirections.actionConversationFragmentToChatFragment(conversationInfo.topic))
    }

}