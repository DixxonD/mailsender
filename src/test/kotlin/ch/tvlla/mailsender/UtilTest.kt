package ch.tvlla.mailsender

import ch.tvlla.mailsender.utils.FileSize
import ch.tvlla.mailsender.utils.Unit
import ch.tvlla.mailsender.utils.Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockMultipartFile

class UtilTest {

    @Test
    fun fileSizeTest(){
        val fileSizeGB = FileSize(5, Unit.GB)
        val fileSizeMB = FileSize(5, Unit.MB)
        val fileSizeKB = FileSize(5, Unit.KB)
        val fileSizeB = FileSize(5, Unit.B)

        assertEquals(5_000_000_000.0, fileSizeGB.bytes)
        assertEquals(5_000_000.0, fileSizeMB.bytes)
        assertEquals(5_000.0, fileSizeKB.bytes)
        assertEquals(5.0, fileSizeB.bytes)

    }

    @Test
    fun convertFileSizeTest(){
        val fileSizeGB = FileSize(5, Unit.GB)
        val fileSizeMB = fileSizeGB.convertTo(Unit.MB)
        assertEquals(5_000.0, fileSizeMB)

        val fileSizeB = FileSize(5, Unit.B)
        val fileSizeKB = fileSizeB.convertTo(Unit.KB)
        assertEquals(0.005, fileSizeKB)

    }

    @Test
    fun isTooLargeTest(){
        val fileContent = "contentOf16Bytes"
        val file = MockMultipartFile("test", fileContent.toByteArray())
        val fileSize = fileContent.length

        val result1 = Util.fileTooLarge(file, FileSize(fileSize + 1, Unit.B))
        assertFalse(result1)

        val result2 = Util.fileTooLarge(file, FileSize(fileSize, Unit.B))
        assertFalse(result2)

        val result3 = Util.fileTooLarge(file, FileSize(fileSize - 1, Unit.B))
        assert(result3)

        val result4 = Util.fileTooLarge(file, FileSize(1.0, Unit.KB))
        assertFalse(result4)
    }

}