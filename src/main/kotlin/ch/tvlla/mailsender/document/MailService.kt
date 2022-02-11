package ch.tvlla.mailsender.document

import ch.tvlla.mailsender.exception.AttachmentAlreadySendException
import ch.tvlla.mailsender.exception.FileTooLargeException
import ch.tvlla.mailsender.mail.EmailServiceImpl
import ch.tvlla.mailsender.utils.FileSize
import ch.tvlla.mailsender.utils.Unit
import ch.tvlla.mailsender.utils.Util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class MailService(private val documentService: DocumentService,
                  private val documentMonitor: DocumentMonitor,
                  private val emilService: EmailServiceImpl) {

    private val logger: Logger = LoggerFactory.getLogger(MailService::class.java)

    @Value("\${custom.mail.target}")
    private val targetAddr = ""

    fun sendMailWithAttachment(attachment: MultipartFile, uploadModel: UploadModel, clientAddr: String){

        documentService.checkFileSize(attachment, FileSize(20, Unit.MB))
        documentService.deleteOldEntries(LocalDateTime.now().minusDays(3))
        documentMonitor.reportAllSavedDocuments()

        val idOptional = documentService.getIdIfAlreadyExists(attachment)
        if(idOptional.isPresent){
            documentService.updateEntry(idOptional.get(), clientAddr)
            logger.warn("a file has been uploaded which has already been seen recently")
            documentMonitor.reportDocumentsMultipleTimesUploaded()
            throw AttachmentAlreadySendException()
        }

        documentService.saveDocumentHash(attachment, clientAddr)
        //emilService.sendMessageWithAttachment(targetAddr, uploadModel, Util.multipartToFile(attachment),attachment.originalFilename?:"unknown")
        logger.info("Mail sent with attachment ${attachment.originalFilename} to $targetAddr")

        }

}

@Service
class DocumentService(private val documentRepository: DocumentRepository){

    fun checkFileSize(attachment: MultipartFile, maxSize: FileSize){
        if(Util.fileTooLarge(attachment, maxSize)){
            throw FileTooLargeException(maxSize)
        }
    }

    fun getIdIfAlreadyExists(attachment: MultipartFile): Optional<Document>{
        val fileContent = attachment.bytes
        val hashCrypto = Util.getSHA256Hash(fileContent)
        val checksum = Util.getCRC32Checksum(fileContent)
        val equalDocs = documentRepository.findBySize(fileContent.size)
            .filter { it.hashCRC == checksum }
            .filter { it.hashCrypto.contentEquals(hashCrypto) }
        return if(equalDocs.isNotEmpty()) Optional.of(equalDocs.last()) else Optional.empty()
    }

    fun saveDocumentHash(attachment: MultipartFile, clientAddr: String){
        val hashCrypto = Util.getSHA256Hash(attachment.bytes)
        val crc = Util.getCRC32Checksum(attachment.bytes)
        val document = Document(0, hashCrypto, crc, attachment.bytes.size, System.currentTimeMillis(), clientAddr)
        documentRepository.save(document)
    }

    @Transactional
    fun updateEntry(document: Document, clientAddr: String){
        document.counterSeen = document.counterSeen + 1
        document.clientIPAddr = clientAddr
        document.lastSeen = System.currentTimeMillis()
        documentRepository.saveAndFlush(document)
    }

    @Transactional
    fun deleteOldEntries(before: LocalDateTime){
        documentRepository.deleteByLastSeenLessThan(before.toEpochSecond(ZoneOffset.UTC) * 1000)
    }

}