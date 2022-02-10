package ch.tvlla.mailsender.exception

class InvalidRequestException : RuntimeException {
    constructor() : super() {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?) : super(message) {}
    constructor(cause: Throwable?) : super(cause) {}

    companion object {

        private const val serialVersionUID = 4088649120307193208L
    }
}