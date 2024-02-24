package kotlinPackage.lec03

import javaPackage.lec03.Person

fun main() {
    //region "기본 타입 캐스팅"
    val num1 = 10
    //val num2: Long = num1 // 타입 매치 에러
    val num2: Long = num1.toLong()

    var num3: Int? = 100
    val num4: Long = num3?.toLong() ?: 0L
    //endregion

    //region "타입 비교"
    fun printAgeIfPerson(obj: Any) {
        if(obj is Person) {
            val person = obj as Person
            println(obj.age)
        }
    }

    // 만약 obj에 null이 들어 올 수 있으면?
    fun printAgeIfPerson(obj: Any?) {
        val person: Person? = obj as? Person
        println(person?.age)

    }
    //endregion

    //region "String interpolation & Indexing"
    val person = javaPackage.lec01.Person("김")
    println("내 이름의 성은 ${person.name}이야")

    val str = """
        ABC
        DBA
        ${person.name}
    """.trimIndent()

    println(str)

    //세부적으로 글자를 가져오고 싶을 때는 아래 처럼 가능
    println(str[0])
    println(str[4])

    //endregion
}