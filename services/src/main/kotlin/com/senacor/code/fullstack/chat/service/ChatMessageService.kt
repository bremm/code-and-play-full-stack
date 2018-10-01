package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChatMessageService(@Autowired private var channelService: ChannelService,
                         @Autowired private var chatMessageRepository: ChatMessageRepository) {

    fun loadChatMessages(channelId: String): List<ChatMessage> {
        if(!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampAsc(channelId)
    }

    fun saveChatMessage(channelId: String, sender: String, message: String): ChatMessage {
        if (!channelService.existsChannel(channelId)) {
            throw ChannelNotFoundException()
        }
        return chatMessageRepository.save(ChatMessage(channelId, sender, message))
    }
}