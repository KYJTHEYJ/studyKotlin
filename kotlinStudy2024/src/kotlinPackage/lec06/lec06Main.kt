package kotlinPackage.lec06

fun main() {
    val numbers = listOf(1, 2, 3)

    //자바의 for-each
    //in 을 쓸 때 iterable 구현된 클래스면 다 가능
    for (number in numbers) {
        println(number)
    }

    println("-----")

    for (i in 1..3) {
        println(i)
    }

    println("-----")

    for (i in 3 downTo 1) {
        println(i)
    }

    println("-----")

    // 2씩 증가
    for (i in 1..5 step 2) {
        println(i)
    }

    println("-----")

    //while은 그냥 동일하다
    var i = 10
    while (i <= 20) {
        println(i)
        i++
    }
}