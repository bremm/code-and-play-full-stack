package com.senacor.code.fullstack.chat.repository

import com.senacor.code.fullstack.chat.domain.Channel
import org.springframework.data.mongodb.repository.MongoRepository


interface ChannelRepository : MongoRepository<Channel, String>