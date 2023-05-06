package com.sudo248.soc_staff.domain.entity.chat

import com.sudo248.base_android.base.ItemDiff
import java.util.*

data class ConversationInfo(
    val topic: String,
    val conversationName: String,
    val conversationImage: String,
    val latestMessage: String,
    val updateTime: Long
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return other is ConversationInfo && other == this
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return other is ConversationInfo && other.topic == topic && other.latestMessage == latestMessage && other.updateTime == updateTime
    }

}