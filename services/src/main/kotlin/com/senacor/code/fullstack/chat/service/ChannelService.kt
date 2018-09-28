package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.repository.ChannelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChannelService(@Autowired private var channelRepository: ChannelRepository) {

    fun loadChannels() = channelRepository.findAll()

    fun existsChannel(channelId: String) = channelRepository.existsById(channelId)
}