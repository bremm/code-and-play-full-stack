package com.senacor.code.fullstack.chat.domain

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import java.time.Instant
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

class ChatMessage(var channelId: String, @NotNull @Email var sender: String, @NotNull @Length(min = 3, max = 140) var message: String) {
    constructor() : this("","","")

    var id: String? = null
    var creationTimestamp: Instant = Instant.now()

    init {
        this.creationTimestamp = Instant.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChatMessage

        if (sender != other.sender) return false
        if (message != other.message) return false
        if (id != other.id) return false
        if (channelId != other.channelId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sender.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + id!!.hashCode()
        result = 31 * result + channelId.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChatMessage(sender='$sender', message='$message', id='$id', channelId='$channelId', creationTimestamp=$creationTimestamp)"
    }

}
