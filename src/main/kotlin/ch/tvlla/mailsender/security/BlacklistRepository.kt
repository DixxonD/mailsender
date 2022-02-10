package ch.tvlla.mailsender.security

import org.springframework.data.jpa.repository.JpaRepository

interface BlacklistRepository: JpaRepository<RemoteClient, String> {
    fun deleteAllByLastSeenLessThan(before: Long)
}