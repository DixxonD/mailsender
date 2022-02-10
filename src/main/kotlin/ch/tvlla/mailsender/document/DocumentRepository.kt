package ch.tvlla.mailsender.document

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository: JpaRepository<Document, Long> {

    override fun findAll(): List<Document>
    fun findBySize(size: Int): List<Document>
    fun deleteByLastSeenLessThan(before: Long)
    fun findByCounterSeenGreaterThan(seenMoreOftenThan: Int): List<Document>

}