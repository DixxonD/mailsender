package ch.tvlla.mailsender.security

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RemoteClient(
    @Id
    val remoteAddr: String,
    @Column
    val lastSeen: Long
) {
}