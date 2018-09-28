package com.senacor.code.fullstack.chat.service

import com.senacor.code.fullstack.chat.domain.ChatMessage
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageServiceTest {
    private var service = ChatMessageService()

    @Test fun fetchChatMessages() {
        var result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }
}