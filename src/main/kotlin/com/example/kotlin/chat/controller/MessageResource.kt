package com.example.kotlin.chat.controller

import com.example.kotlin.chat.service.MessageService
import com.example.kotlin.chat.service.MessageVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onStart
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.*

@RestController
//@RequestMapping("/api/v1/messages")
@MessageMapping("api.v1.messages")
class MessageResource(val messageService: MessageService) {

//    @GetMapping
//    fun latest(@RequestParam(value = "lastMessageId", defaultValue = "") lastMessageId: String): ResponseEntity<Flow<MessageVM>> {
//        val messages = if (lastMessageId.isNotEmpty()) {
//            messageService.after(lastMessageId)
//        } else {
//            messageService.latest()
//        }
//
//        return if (messages.isEmpty()) {
//            with(ResponseEntity.noContent()) {
//                header("lastMessageId", lastMessageId)
//                build<Flow<MessageVM>>()
//            }
//        } else {
//            with(ResponseEntity.ok()) {
//                header("lastMessageId", messages.last().id)
//                body(messages)
//            }
//        }
//    }
//
//    @PostMapping
//    fun post(@RequestBody message: MessageVM) {
//        messageService.post(message)
//    }

    @MessageMapping("stream")
    suspend fun receive(@Payload inboundMessages: Flow<MessageVM>) =
        messageService.post(inboundMessages)

    @MessageMapping("stream")
    fun send(): Flow<MessageVM> = messageService
        .stream()
        .onStart {
            emitAll(messageService.latest())
        }
}
