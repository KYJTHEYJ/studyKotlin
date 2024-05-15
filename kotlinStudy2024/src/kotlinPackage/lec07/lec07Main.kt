package kotlinPackage.lec07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun parseIntThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("숫자로 바꿀수가 없습니다. ${str}")
    }
}

//try-catch 도 expression
fun parseIntOrThrow2(str: String): Int? {
    return try{
        str.toInt()
    } catch (e: NumberFormatException){
        null
    }
}

//Java 와 달리 unchecked Exception 으로 모두를 간주한다. 즉 throws 라는 개념이 필요가 없다.
class FilePrinter {
    fun readFile() {
        val currentFile = File(".")
        val file = File(currentFile.absolutePath + "/a.txt")
        val reader = BufferedReader(FileReader(file))
        println(reader.readLine())
        reader.close()
    }

    // try-with-resource 이라는 개념은 없다. 비슷하게 사용시 코틀린은 use 라는 키워드를 사용한다
    // 자원 반납이 auto
    fun readFile(path: String) {
        BufferedReader(FileReader(path)).use {
            println(it.readLine())
        }
    }
}

fun main() {
    FilePrinter().readFile()
    FilePrinter().readFile("./a.txt")
}
