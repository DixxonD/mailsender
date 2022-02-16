package ch.tvlla.mailsender.resultCollector

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Result(

    @Id
    @GeneratedValue
    val id: Long,
    val result: String,
    val name: String,
    val discipline: String,
    val competition: String,
    val info: String,
    val addedAt: LocalDateTime
){
    @Column
    val hash = hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (result != other.result) return false
        if (name != other.name) return false
        if (discipline != other.discipline) return false
        if (competition != other.competition) return false
        if (info != other.info) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = result.hashCode()
        result1 = 31 * result1 + name.hashCode()
        result1 = 31 * result1 + discipline.hashCode()
        result1 = 31 * result1 + competition.hashCode()
        result1 = 31 * result1 + info.hashCode()
        return result1
    }

    override fun toString(): String {
        return "$name: $discipline - $result ${if(info.isNotBlank()) "($info)" else ""} ($competition)"
    }

}