package kotlinPackage.lec19

import javaPackage.lec17.Fruit

//import ~.printHelloWorld as printHelloWorldA
//import ~2.printHelloWorld as printHelloWorldB

// 긴 타입을 alias 화 가능
typealias filters = (Fruit) -> Boolean

fun longParamFunc(name: String, filter: filters) {

}

data class Person(
    val name: String, val age: Int
)

class Person2(
    val name: String, val age: Int
) {
    operator fun component1(): String {
        return this.name
    }

    operator fun component2(): Int {
        return this.age
    }
}

//다른 패키지의 같은 이름 함수를 가져오고 싶을 때 : as import
fun main() {
    //printHelloWorldA()
    //printHelloWorldB()
    //로 사용

    //구조분해 문법
    val person = Person("KYJ", 90)

    // map의 key, value 를 따로 받는 entries 도 구조분해임

    //data class 를 생성시 ComponentN 함수를 자동으로 생성함
    //val name = person.component1() ...
    //자동으로 뭐 네임을 매칭시켜주는게 아니라 그냥 순서대로 하는 것이므로 유의
    val (name, age) = person
    println("${name}, ${age}")

    val numbers = listOf(1, 2, 3)

    // break, continue 가 자바랑 다른 점
    // list 함수에서의 forEach
    numbers.map { number -> number + 1 }
        .forEach { number -> println(number) }

    for (number in numbers) {
        if(number == 2) break
    }

    // break, continue 는 loop 의 inside 에서만 가능
    // 사용하려면 run{} 으로 감싸 준 후 return@run, return@forEach 의 형식으로 사용해야함
    run {
        numbers.forEach { number ->
            if (number == 2) {
                return@run
            }
        }
    }

    // @ -> label
    // 코틀린에서는 @ 키워드 이후 이름을 붙여 break, continue, return 의 효과를 낼 수 있음

    // 함수 구문 내에 특정 조건에 return null 일 경우는 takeIf 로 대체할 수 있다
    fun getNumberOrNull(number: Int): Int? {
        return if(number <= 0) {
            null
        } else {
            number
        }
    }

    // 조건 만족 시 해당 값 반환, 아니면 null
    fun getNumberOrNull2(number: Int): Int? {
        return number.takeIf { number > 0 }
    }

    // 조건 만족 시 해당 값 null, 아니면 값
    fun getNumberOrNull3(number: Int): Int? {
        return number.takeUnless { number <= 0 }
    }
}
