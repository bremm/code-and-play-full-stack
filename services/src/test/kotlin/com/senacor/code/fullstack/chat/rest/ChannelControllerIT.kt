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
class ChannelControllerIT {

    @Autowired
    private lateinit var wac: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    @Throws(Exception::class)
    fun loadChannels() {
        mockMvc.perform(get("/api/v1/channels")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("general"))
                .andExpect(jsonPath("$[1].name").value("dev"))
                .andExpect(jsonPath("$[2].name").value("humor"))
    }

}