package org.overrun

import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel





const val max = 1024

class Download {
    fun downloadUsingStream(urlStr: String, file: String) {
        val url = URL(urlStr)
        val bis = BufferedInputStream(url.openStream())
        val fis = FileOutputStream("$file${File.separator}${getLocalPathName(urlStr)}")
        val buffer = ByteArray(1024)
        var count = 0
        while (bis.read(buffer, 0, 1024).also { count = it } != -1) {
            fis.write(buffer, 0, count)
        }
        fis.close()
        bis.close()
    }
    fun downloadUsingNIO(urlStr: String, file: String) {
        val url = URL(urlStr)
        val rbc: ReadableByteChannel = Channels.newChannel(url.openStream())
        val fos = FileOutputStream("$file${File.separator}${getLocalPathName(urlStr)}")
        fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
        fos.close()
        rbc.close()
    }

    fun getLocalPathName(urlStr: String): String {
        val split = urlStr.split('/')
        return split[split.size - 1]
    }


}

fun main() {
    var download = Download()
//    download.downloadUsingNIO("", "D:${File.separator}a")
//    download.downloadUsingNIO("https://services.gradle.org/distributions/gradle-7.5.1-bin.zip", "D:${File.separator}a")
//    download.download("D:\\a", "https://services.gradle.org/distributions/gradle-7.5.1-bin.zip", 1)
}