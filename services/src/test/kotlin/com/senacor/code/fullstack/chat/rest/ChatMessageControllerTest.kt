package com.senacor.code.fullstack.chat.rest

import com.senacor.code.fullstack.chat.domain.ChatMessage
import com.senacor.code.fullstack.chat.service.ChatMessageService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.http.HttpStatus
import java.net.URI
import java.net.URISyntaxException

class ChatMessageControllerTest {

    private var serviceMock = mockk<ChatMessageService>()

    private var controller = ChatMessageController(serviceMock)

    @Test
    fun loadChannelMessages() {
        val expected = listOf<ChatMessage>(ChatMessage(), ChatMessage())

        every { serviceMock.loadChatMessages("dev") } returns expected

        val result = controller.loadChatMessages("dev")

        assertEquals(expected, result)
        verify { serviceMock.loadChatMessages("dev") }
    }

    @Test
    @Throws(URISyntaxException::class)
    fun newChatMessages() {
        val savedMessage = ChatMessage("dev", "sender@test.de", "Hello World!")
        savedMessage.id = "id-of-msg"
        //when(serviceMock.createChatMessage("dev", "sender@test.de", "Hello Word!")).thenReturn(savedMessage);
        every { serviceMock.saveChatMessage(any(), any(), any()) } returns savedMessage

        val result = controller.newChatMessages("dev", ChatMessage(null, "sender@test.de", "Hello World!"))

        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals(URI("/api/v1/channels/dev/messages/id-of-msg"), result.headers.location)
        verify { serviceMock.saveChatMessage("dev", "sender@test.de", "Hello World!") }
    }
}