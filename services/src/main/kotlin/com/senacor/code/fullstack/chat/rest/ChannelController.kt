package com.senacor.code.fullstack.chat.rest

import com.senacor.code.fullstack.chat.service.ChannelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/channels")
class ChannelController(@Autowired private var channelService: ChannelService) {

    @GetMapping
    fun loadChannels() = channelService.loadChannels()
}