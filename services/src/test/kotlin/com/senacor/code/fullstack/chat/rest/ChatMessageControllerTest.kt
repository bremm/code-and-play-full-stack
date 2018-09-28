package com.senacor.code.fullstack.chat.rest

import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.service.ChatMessageService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageControllerTest {

    private var serviceMock = mockk<ChatMessageService>()

    private var controller = ChatMessageController(serviceMock)

    @Test
    fun loadChannelMessages() {
        var expected = listOf<ChatMessage>(ChatMessage(), ChatMessage())

        every { serviceMock.loadChatMessages("dev") } returns expected

        var result = controller.loadChatMessages("dev")

        assertEquals(expected, result)
        verify { serviceMock.loadChatMessages("dev") }
    }
}