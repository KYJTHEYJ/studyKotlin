package kotlinPackage.lec01

import javaPackage.lec01.Person

fun main() {
    //코틀린은 초기화가 동시에 이루어져야함
    var number1: Int = 10 // 모든 변수는 우선 val로 만들고 추 후 필요할 때 var로 해라 (클린 코드)
    println(number1)

    //코틀린은 프로그래머가 boxing, unboxing을 생각하지 않아도 적절히 처리해줌
    //자동으로 레퍼런스 타입과 프리미티브 타입을 적절히 구분해줌
    val number2: Int = 20

    //null-safety 를 위해서 ? 를 붙일 수 있다
    var number3: Long? = null

    var person = Person("yj")
}