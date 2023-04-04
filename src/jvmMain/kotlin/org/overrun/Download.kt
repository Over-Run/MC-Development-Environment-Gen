package org.overrun

import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL


class Download {

    fun download(path: String, url: String, tNum: Int) {
        val file = File(path + getFileExtName(url))
        val accessFile = RandomAccessFile(file, "rwd")
        val ul = URL(url)
        val conn: HttpURLConnection = ul.openConnection() as HttpURLConnection
        conn.connectTimeout = 2000
        conn.requestMethod = "GET"
        val len = conn.contentLength
        accessFile.setLength(len.toLong())
        accessFile.close()
        val block = (len + tNum - 1) / tNum;
        for (i in 0 until tNum) {
            val a = i
            Thread(object : Runnable {
                var start = block * a // 开始位置
                var end = block * (a + 1) - 1 // 结束位置
                override fun run() {
                    var conn2: HttpURLConnection? = null
                    var accessFile2: RandomAccessFile? = null
                    var `in`: InputStream? = null
                    conn2 = ul.openConnection() as HttpURLConnection
                    conn2.connectTimeout = 2000
                    conn2.requestMethod = "GET"
                    conn2.setRequestProperty(
                        "Range", "bytes=$start-$end"
                    )
                    `in` = conn2.inputStream
                    val data = ByteArray(1024)
                    var ln:Int
                    accessFile2 = RandomAccessFile(file, "rwd")
                    accessFile2.seek(start.toLong())

                    while (`in`.read(data).also { ln = it } != -1) {
                        accessFile2.write(data, 0, ln)
                    }
                    println("Thread ${a} has download ")
                    accessFile2.close()
                    `in`.close()
                }
            }).start()
        }
    }

    private fun getFileExtName(path: String): String {
        return path.substring(path.lastIndexOf("."))
    }

}

fun main() {
    var download = Download()
    download.download("D:\\a", "http://static.ishare.down.sina.com.cn/5585234.txt?ssig=f7CrV3UL8%2B&Expires=1347724800&KID=sina,ishare&ip=1347592902,117.40.138.&fn=%E5%8E%9A%E9%BB%91%E5%AD%A6.TXT", 3)
}