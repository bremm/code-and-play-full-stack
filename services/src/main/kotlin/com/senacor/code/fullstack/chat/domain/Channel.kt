package com.senacor.code.fullstack.chat.domain

import org.springframework.data.annotation.Id

class Channel(@Id var channelId: String, var name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Channel

        if (channelId != other.channelId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channelId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}