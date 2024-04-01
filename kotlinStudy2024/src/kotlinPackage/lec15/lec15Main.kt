package kotlinPackage.lec15

import java.util.*

// 배열
var array: Array<Int> = arrayOf<Int>(100, 200)

// 컬렉션
// mutable, immutable 한 것으로 나뉨
// immutable 하다고 해도 컬렉션 안에 레퍼런스 타입 안의 필드 값은 변경이 가능하다
var numbers = listOf(100, 200) // immutable
var mutableNumbers = mutableListOf(100, 200) // mutable

var emptyNumbers = emptyList<Int>()

var setNumber = setOf(100, 200)
var mutableSetNumber = mutableSetOf(100, 200)

var mapNumber = mapOf<Int, String>(1 to "K", 2 to "Y", 3 to "J")
var mapNumber2 = mutableMapOf<Int, String>()
var mapNumber3 = mapOf<Int, String>(Pair(1, "K"), Pair(2, "Y"), Pair(3, "J"))

//정적 팩토리 호출
var mapNumber4 = mapOf(Pair(1, "K"), Pair(2, "Y"), Pair(3, "J"))

private fun emptys(emptyList: List<Int>) {}

fun main() {
    for (i in array.indices) {
        println("${i}, ${array[i]}")
    }

    for ((index, value) in array.withIndex()) {
        println("${index}, $value")
    }

    array.plus(300) // 마지막에 element 추가

    // ----------

    emptys(emptyList()) // -> emptyList 의 타입 추론을 알아서 해줌 (emptys func 에 정의 되어있음)

    numbers.get(0)
    numbers[0]

    for(number in numbers) {
        println(number)
    }

    for((index, value) in numbers.withIndex()) {
        println("$index , $value")
    }

    // -----------

    mutableNumbers.add(300)

    // -----------

    mapNumber2[1] = "K"
    mapNumber2[2] = "Y"
    mapNumber2[3] = "J"

    mapNumber2.put(4, "STUDY")

    for(key in mapNumber.keys) {
        println(key)
        println(mapNumber[key])
    }

    for((key, value) in mapNumber.entries) {
        println("$key , $value")
    }

    // 만약 자바로 코드를 넘길 때 불변이 필요하다면 아래와 같이 Collections.unmodifableXXX 활용
    // 자바로 넘어갈 때 nullable, non-nullable의 적용 범위의 차이로 값이 추가 되어 혼선이 올수 있음
    // List<Integer> -> List<Int>? 인지 List<Int?> 인지 List<Int?>? 인지 모름
    mapNumber = Collections.unmodifiableMap(mapNumber)
}