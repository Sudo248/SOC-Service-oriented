package com.sudo248.soc_staff.ui.activity.main.fragment.adater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.soc_staff.databinding.ItemRecentChatBinding
import com.sudo248.soc_staff.domain.entity.chat.ConversationInfo
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage
import com.sudo248.soc_staff.ui.util.Utils
import java.util.*

class ConversationAdapter(
    private val onItemClick: (ConversationInfo) -> Unit
) : BaseListAdapter<ConversationInfo, ConversationAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemRecentChatBinding) : BaseViewHolder<ConversationInfo, ItemRecentChatBinding>(binding) {
        override fun onBind(item: ConversationInfo) {
            binding.apply {
                loadImage(imgAvatar, item.conversationImage)
                txtTitle.text = item.conversationName
                txtDescription.text = item.latestMessage
                txtTime.text = Utils.formatTime(Date(item.updateTime))
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecentChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}