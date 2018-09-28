package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.Channel
import com.senacor.code.fullstack.chat.repository.ChannelRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChannelServiceTest {

    private val repository = mockk<ChannelRepository>()

    private val service = ChannelService(repository)

    @Before
    fun setup() {
        every { repository.findAll() } returns listOf(Channel("general", "general"),
                Channel("dev", "dev"),
                Channel("humor", "humor"))
    }

    @Test
    fun fetchAllChannels() {
        val expected = listOf(
                Channel("general", "general"),
                Channel("dev", "dev"),
                Channel("humor", "humor"))

        val result = service.loadChannels()

        assertEquals(expected, result)
    }

    @Test
    fun existsChannel() {
        every { repository.existsById("dev") } returns true

        val result = service.existsChannel("dev")

        assertTrue(result)
    }

    @Test
    fun existsChannelForNotExistingChannel() {
        every { repository.existsById("not-a-channel") } returns false

        val result = service.existsChannel("not-a-channel")

        assertFalse(result)
    }

}