package com.example.kotlin.chat.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author gcdd1993
 */
@FeignClient("github", url = "https://api.github.com")
interface GithubApi {

    @GetMapping("/repos/vmg/redcarpet/issues")
    fun issues(@RequestParam state: String = "closed"): Any?
}