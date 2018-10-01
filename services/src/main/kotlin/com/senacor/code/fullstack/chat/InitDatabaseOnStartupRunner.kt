package com.senacor.code.fullstack.chat

import com.senacor.code.fullstack.chat.domain.Channel
import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.repository.ChannelRepository
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitDatabaseOnStartupRunner : CommandLineRunner {

    @Autowired
    private lateinit var channelRepository: ChannelRepository

    @Autowired
    private lateinit var chatMessageRepository: ChatMessageRepository

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        // create some channels
        channelRepository.save(Channel("general", "general"))
        channelRepository.save(Channel("dev", "dev"))
        channelRepository.save(Channel("humor", "humor"))

        // fetch all channels
        LOGGER.info("Channels in Mongo DB:")
        for (msg in channelRepository.findAll()) {
            LOGGER.info("    $msg")
        }

        // create some messages
        chatMessageRepository.save(ChatMessage("dev", "sender@test.de", "Hello"))
        chatMessageRepository.save(ChatMessage("dev", "sender@test.de", "World!"))
        chatMessageRepository.save(ChatMessage("general", "sender@test.de", "Did you ever tried Spring Boot 2.0.0?"))

        // fetch all channels
        LOGGER.info("ChatMessages in Mongo DB:")
        for (msg in chatMessageRepository.findAll()) {
            LOGGER.info("    $msg")
        }
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(InitDatabaseOnStartupRunner::class.java)
    }
}