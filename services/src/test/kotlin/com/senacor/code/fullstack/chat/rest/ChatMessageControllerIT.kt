package com.senacor.code.fullstack.chat.rest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class ChatMessageControllerIT {
    @Autowired private lateinit var wac: WebApplicationContext
    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun loadMessages() {
        val channel = "dev"

        mockMvc.perform(get("/api/v1/channels/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].message").value("Hello"))
                .andExpect(jsonPath("$[1].message").value("World!"))

    }

    @Test
    fun loadMessagesForNotExistingChannel() {
        val channel = "not-a-channel"
        mockMvc.perform(get("/api/v1/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound)
    }
}