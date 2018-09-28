package com.senacor.code.fullstack.chat.rest

import com.senacor.code.fullstack.chat.service.ChatMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/channels/{channel}/messages")
class ChatMessageController(@Autowired private var messageService: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channel") channel: String) = messageService.loadChatMessages(channel)
}