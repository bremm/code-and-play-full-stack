package com.senacor.code.fullstack.chat.repository

import com.senacor.code.fullstack.chat.ChatApplication
import com.senacor.code.fullstack.chat.domain.ChatMessage
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
import java.time.Instant

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = [ChatApplication::class])
class ChatMessageRepositoryIT {

    @Autowired
    private lateinit var repository: ChatMessageRepository

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
    fun saveAndLoadChatMessages() {
        assertTrue(repository.findAll().isEmpty())

        val messageOne = repository.save(ChatMessage("dev", "sender@test.de", "Hello"))
        messageOne.creationTimestamp = Instant.now().minusSeconds(35)
        assertEquals(1, repository.findAll().size.toLong())

        val messageTwo = repository.save(ChatMessage("dev", "sender@test.de", "World!"))
        messageTwo.creationTimestamp = Instant.now()

        val result = repository.findAll()
        assertEquals(listOf(messageOne, messageTwo), result)
    }

}