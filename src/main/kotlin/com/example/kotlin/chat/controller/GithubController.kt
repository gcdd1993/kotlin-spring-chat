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

    // 写法一：使用Mono，手动创建mono上下文，走的是Kotlin Coroutine
//    @GetMapping("/issues")
//    fun issues(): Mono<Any?> {
//        return mono {
//            githubApi.issues()
//                .also { print("github issues size: ${(it as List<Any?>).size}") }
//        }
//    }

    // 写法二，在方法前加上 suspend ，虽然返回的不是Mono，但是suspend会包装成Mono<*>
    @GetMapping("/issues")
    suspend fun issues(): Any? = githubApi.issues()
        .also {
            print("github issues size: ${(it as List<Any?>).size}")
        }

    // 错误写法，返回的是两次Mono包装，导致出错
//    @GetMapping("/issues")
//    suspend fun issues(): Mono<Any?> = mono {
//        githubApi.issues()
//            .also {
//                print("github issues size: ${(it as List<Any?>).size}")
//            }
//    }

}