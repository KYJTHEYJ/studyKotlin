package kotlinPackage.lec04

import javaPackage.lec04.JavaMoney

fun main() {
    val money1 = JavaMoney(3_00L)
    val money2 = JavaMoney(2_00L)

    if(money1 > money2) {
        println("객체비교에서 자동으로 CompareTo 호출")
    }

    if(money1 == money2) {
        println("동등성 비교에서도 자동으로 Equals 호출")
    }

    val money3 = Money(1_000L)
    val money4 = Money(2_000L)

    // 연산자를 사용해도 무방
    println(money3 + money4)
}