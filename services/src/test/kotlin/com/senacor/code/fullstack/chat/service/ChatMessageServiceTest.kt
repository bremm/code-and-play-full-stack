package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageServiceTest {
    private var channelServiceMock = mockk<ChannelService>()

    private var chatMessageRepository = mockk<ChatMessageRepository>()
    private var chatMessageService = ChatMessageService(channelServiceMock, chatMessageRepository)

    @Test fun fetchChatMessages() {
        every { channelServiceMock.existsChannel("dev") } returns true
        val expectedList = listOf<ChatMessage>(ChatMessage("dev", "s@t.de", "Hello"),
                ChatMessage("dev", "s@t.de", "World!"))

        every { chatMessageRepository.findByChannelIdOrderByCreationTimestampAsc("dev") } returns expectedList

        val result = chatMessageService.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }

    @Test(expected = ChannelNotFoundException::class)
    @Throws(ChannelNotFoundException::class)
    fun loadChatMessagesThrowsExceptionIfChannelNotExist() {
        every { channelServiceMock.existsChannel("not-a-channel") } returns false

        chatMessageService.loadChatMessages("not-a-channel")
    }
}