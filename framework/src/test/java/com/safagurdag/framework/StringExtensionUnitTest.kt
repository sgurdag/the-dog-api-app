package com.safagurdag.framework

import com.safagurdag.framework.extension.toHashMD5
import org.junit.Test

import org.junit.Assert.*
import java.security.MessageDigest

class StringExtensionUnitTest {
    @Test
    fun checkMd5Function() {
        val rawData = "dummy text for md5 function test"
        val rawDataMD5 = "8598e17f7eb33725f2d105abe9e0640f"

        assertEquals(rawData.toHashMD5(),rawDataMD5)
    }
}