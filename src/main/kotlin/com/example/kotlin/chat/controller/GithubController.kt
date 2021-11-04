package com.example.kotlin.chat.controller

import com.example.kotlin.chat.feign.GithubApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author gcdd1993
 */
@RestController
@RequestMapping("/api/github")
class GithubController(
    private val githubApi: GithubApi
) {

    @GetMapping("/issues")
    suspend fun issues(): Any? {
        return githubApi.issues()
            .also { print("github issues size: ${(it as Array<Any?>).size}") }
    }

}