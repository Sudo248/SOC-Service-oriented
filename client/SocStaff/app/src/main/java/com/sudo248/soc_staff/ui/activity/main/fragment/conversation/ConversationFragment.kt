package com.sudo248.soc_staff.ui.activity.main.fragment.conversation

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.databinding.FragmentConversationBinding
import com.sudo248.soc_staff.domain.entity.chat.Conversation
import com.sudo248.soc_staff.ui.activity.main.fragment.adater.ConversationAdapter
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversationFragment : BaseFragment<FragmentConversationBinding, ConversationViewModel>() {
    override val viewModel: ConversationViewModel by viewModels()
    override val enableStateScreen: Boolean
        get() = true

    private val adapter: ConversationAdapter by lazy {
        ConversationAdapter{
            viewModel.openChat(it)
        }
    }

    override fun initView() {
        binding.rcvRecentChats.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllConversation()
        }
        binding.imgBack.setOnClickListener {
            back()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.conversation.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        }
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = requireContext(),
                    message = message
                )
            }
        }
    }
}