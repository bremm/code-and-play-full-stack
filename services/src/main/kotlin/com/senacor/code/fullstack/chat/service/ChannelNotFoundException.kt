package com.senacor.code.fullstack.chat.service

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Channel")
class ChannelNotFoundException : RuntimeException()