package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageServiceTest {
    private var channelServiceMock = mockk<ChannelService>()
    private var service = ChatMessageService(channelServiceMock)

    @Test fun fetchChatMessages() {
        every { channelServiceMock.existsChannel("dev") } returns true
        val result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }

    @Test(expected = ChannelNotFoundException::class)
    @Throws(ChannelNotFoundException::class)
    fun loadChatMessagesThrowsExceptionIfChannelNotExist() {
        every { channelServiceMock.existsChannel("not-a-channel") } returns false

        service.loadChatMessages("not-a-channel")
    }
}