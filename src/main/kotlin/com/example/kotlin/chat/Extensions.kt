package com.example.kotlin.chat

import com.example.kotlin.chat.repository.ContentType
import com.example.kotlin.chat.repository.Message
import com.example.kotlin.chat.service.MessageVM
import com.example.kotlin.chat.service.UserVM
import java.net.URL

fun MessageVM.asDomainObject(contentType: ContentType = ContentType.PLAIN): Message = Message(
    content,
    contentType,
    sent,
    user.name,
    user.avatarImageLink.toString(),
    id
)

fun Message.asViewModel(): MessageVM = MessageVM(
    content,
    UserVM(username, URL(userAvatarImageLink)),
    sent,
    id
)