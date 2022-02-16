package ch.tvlla.exception

import ch.tvlla.mailsender.mailsender.utils.FileSize
open class CustomException(): RuntimeException(){

}

class FileTooLargeException(maxSize: FileSize): CustomException() {
    override val message = "Die hochgeladene Datei ist zu gross. Maximal erlaubt sind $maxSize"

}

class AttachmentAlreadySendException: CustomException(){
    override val message = "Diese Datei wurde bereits hochgeladen."
}