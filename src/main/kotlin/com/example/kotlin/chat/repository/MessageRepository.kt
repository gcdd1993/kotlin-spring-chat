package com.example.kotlin.chat.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface MessageRepository : CoroutineCrudRepository<Message, String> {

    // language=SQL
    @Query(
        """
        select * from (
            select * from MESSAGES
            order by "SENT" desc
            limit 10
        ) order by "SENT"
    """
    )
    suspend fun findLatest(): List<Message>

    // language=SQL
    @Query(
        """
        select * from (
            select * from MESSAGES
            where SENT > (select SENT from MESSAGES where ID = :id)
            order by "SENT" desc
        ) order by "SENT"
    """
    )
    suspend fun findLatest(@Param("id") id: String): List<Message>
}