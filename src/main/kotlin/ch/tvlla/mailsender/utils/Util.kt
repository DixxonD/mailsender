package ch.tvlla.mailsender.utils

import org.slf4j.Logger
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.zip.CRC32
import java.util.zip.CheckedInputStream
import javax.servlet.http.HttpServletRequest


const val LOG_SEPARATOR: String = "=================\n"

class Util {

    companion object{

        fun fileTooLarge(file: MultipartFile, maxFileSize: FileSize) = file.size > maxFileSize.bytes

        fun requestLogger(logger: Logger, request: HttpServletRequest){
            var message = LOG_SEPARATOR
            message +=  "User with IP ${request.remoteAddr} ask for '${request.method} ${request.servletPath}'\n"
            logger.info(message)

        }

        fun getCRC32Checksum(bytes: ByteArray): Long {
            val crc32 = CRC32()
            crc32.update(bytes, 0, bytes.size)
            return crc32.value
        }

        fun getSHA256Hash(bytes: ByteArray): ByteArray {
            val sha256 = MessageDigest.getInstance("SHA-256")
            return  sha256.digest(bytes)
        }


        fun multipartToFile(multipartFile: MultipartFile): File {
            val convFile = File(System.getProperty("java.io.tmpdir") + "/${multipartFile.name}")
            multipartFile.transferTo(convFile)
            return convFile
        }

    }
}

