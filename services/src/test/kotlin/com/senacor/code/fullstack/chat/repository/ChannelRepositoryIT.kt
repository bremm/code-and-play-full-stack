package com.senacor.code.fullstack.chat.repository

import com.senacor.code.fullstack.chat.ChatApplication
import com.senacor.code.fullstack.chat.domain.Channel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = arrayOf(ChatApplication::class))
class ChannelRepositoryIT {

    @Autowired
    private lateinit var repository: ChannelRepository

    @Before
    fun setup() {
        // ensure we are staring without any chat messages
        repository.deleteAll()
    }

    @After
    fun cleanup() {
        repository.deleteAll()
    }

    @Test
    fun saveAndLoadChannels() {
        assertTrue(repository.findAll().isEmpty())

        val channelOne = repository.save(Channel("dev-id", "dev-name"))
        assertEquals(1, repository.findAll().size.toLong())

        val channelTwo = repository.save(Channel("general-id", "general-name"))

        assertEquals(listOf(channelOne, channelTwo), repository.findAll())
    }

}