package kotlinPackage.lec02

import javaPackage.lec02.Person

fun main() {
    val str: String? = "ABCD"

    //println(str.length)
    //println(str?.length)
    println(str?.length ?: 0)

    //region "JavaLec02 Person 클래스 참조"
    val person = Person("공부하는 나")
    startWithA(person.name ?: "test")
    //endregion
}

fun startsWithA1(str: String?): Boolean {
    if (str == null) {
        throw IllegalArgumentException("null이 들어왔습니다")
    }

    return str.startsWith("A")
}

fun startsWithA1MoreKotlin(str: String?): Boolean {
    return str?.startsWith("A") ?: throw IllegalArgumentException("null이 들어왔습니다")
}

fun startsWithA2(str: String?): Boolean? {
    if (str == null) {
        return null
    }

    return str.startsWith("A")
}

fun startsWithA2MoreKotlin(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
    if (str == null) {
        return false
    }

    return str.startsWith("A")
}

fun startsWithA3MoreKotlin(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

fun startWith(str: String?): Boolean {
    return str!!.startsWith("A")
}

//region "JavaLec02 Person 클래스 참조"
fun startWithA(str: String): Boolean {
    return str.startsWith("A")
}
//endregion
