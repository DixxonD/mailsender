package ch.tvlla.mailsender.resultCollector

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ResultRepository: JpaRepository<Result, Long> {

    fun existsByHash(hashValue: Int): Boolean
    fun findByAddedAtBefore(before: LocalDateTime): List<Result>
    //fun deleteByAddedAtBefore(before: LocalDateTime)
}