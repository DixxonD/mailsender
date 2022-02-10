package ch.tvlla.mailsender.exception

open class CustomException(): RuntimeException(){

}

class FileTooLargeException: CustomException() {
    override val message = "File too large."

}

class AttachmentAlreadySendException: CustomException(){
    override val message = "File already send"
}