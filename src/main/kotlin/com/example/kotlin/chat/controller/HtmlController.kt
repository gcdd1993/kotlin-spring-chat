package com.example.kotlin.chat.controller

import com.example.kotlin.chat.service.MessageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(val messageService: MessageService) {

    @GetMapping("/")
    suspend fun index(model: Model): String {
//        val messages: List<MessageVM> = messageService.latest()
//
//        model["messages"] = messages
//        model["lastMessageId"] = messages.lastOrNull()?.id ?: ""
//
//        return "chat"
        // implemented in src/main/resources/templates/chatrs.html
        return "chatrs"
    }
}