package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage

class ChatMessageService {

    companion object {
        val messages = listOf(
                ChatMessage("dev", "sender@test.de", "Hello"),
                ChatMessage("dev", "sender@test.de", "World!"))
    }

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        return messages
    }
}