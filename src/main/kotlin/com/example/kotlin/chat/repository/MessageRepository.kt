package com.example.kotlin.chat.repository

import kotlinx.coroutines.flow.Flow
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
    fun findLatest(): Flow<Message>

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
    fun findLatest(@Param("id") id: String): Flow<Message>
}