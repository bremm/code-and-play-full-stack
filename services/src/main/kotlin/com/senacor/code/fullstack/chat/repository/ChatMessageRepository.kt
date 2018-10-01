package com.senacor.code.fullstack.chat.repository

import com.senacor.code.fullstack.chat.domain.ChatMessage
import org.springframework.data.mongodb.repository.MongoRepository

interface ChatMessageRepository : MongoRepository<ChatMessage, String> {
    fun findByChannelIdOrderByCreationTimestampDesc(channelId: String): List<ChatMessage>
}