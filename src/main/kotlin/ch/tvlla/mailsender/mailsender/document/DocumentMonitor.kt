package ch.tvlla.mailsender.mailsender.document

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DocumentMonitor(private val documentRepository: DocumentRepository) {

    private val logger: Logger = LoggerFactory.getLogger(DocumentMonitor::class.java)

    private val template = "\nFile with id %s was uploaded %s times in total, last uploaded by %s"

    fun reportAllSavedDocuments(){
        val docs = documentRepository.findAll()
        logReport(docs)
    }

    fun reportDocumentsMultipleTimesUploaded(){
        val docs = documentRepository.findByCounterSeenGreaterThan(-1)
        logReport(docs)
    }

    private fun logReport(data: List<Document>){
        var report = ""
        data.forEach { report += String.format(template, it.id, it.counterSeen, it.clientIPAddr) }
        logger.info(report)
    }

}