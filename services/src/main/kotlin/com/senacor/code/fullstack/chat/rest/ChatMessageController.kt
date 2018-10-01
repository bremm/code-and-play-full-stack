package com.senacor.code.fullstack.chat.rest

import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.service.ChatMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping(ChatMessageController.CHAT_MESSAGES_PATH)
class ChatMessageController(@Autowired private var messageService: ChatMessageService) {

    companion object {
        const val CHAT_MESSAGES_PATH = "/api/v1/channels/{channel}/messages"
    }
    @GetMapping
    fun loadChatMessages(@PathVariable("channel") channel: String) = messageService.loadChatMessages(channel)

    @PostMapping
    fun newChatMessages(@PathVariable("channel") channel: String, @RequestBody chatMessage: ChatMessage): ResponseEntity<Void> {
        val newChatMessage = messageService.saveChatMessage(channel, chatMessage.sender, chatMessage.message)

        val location = UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH)
                .pathSegment(newChatMessage.id).buildAndExpand(channel)
        return ResponseEntity.created(location.toUri()).build()
    }
}