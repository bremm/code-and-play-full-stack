package com.senacor.code.fullstack.chat

import com.senacor.code.fullstack.chat.domain.Channel
import com.senacor.code.fullstack.chat.repository.ChannelRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitDatabaseOnStartupRunner : CommandLineRunner {

    @Autowired
    private lateinit var repository: ChannelRepository

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        // create some channels
        repository.save(Channel("general", "general"))
        repository.save(Channel("dev", "dev"))
        repository.save(Channel("humor", "humor"))

        // fetch all channels
        LOGGER.info("Channels in Mongo DB:")
        for (msg in repository.findAll()) {
            LOGGER.info("    $msg")
        }
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(InitDatabaseOnStartupRunner::class.java)
    }
}