package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChatMessageService(@Autowired private var channelService: ChannelService) {

    companion object {
        val messages = listOf(
                ChatMessage("dev", "sender@test.de", "Hello"),
                ChatMessage("dev", "sender@test.de", "World!"))
    }

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if(!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return messages
    }
}