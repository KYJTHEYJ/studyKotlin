package kotlinPackage.lec16

import javaPackage.lec16.Person
import kotlinPackage.lec15.emptyNumbers
import javax.management.StringValueExp

// 확장 함수
// Kotlin 은 Java 와 100% 호환 추구
// 기존 Java 코드 위에 자연스럽게 코틀린 코드 추가는 못할까?
// -> 어떤 클래스 안에 있는 메소드처럼 호출 할 수 있지만 함수는 밖에 만들게 하자
// 대신 확장함수는 클래스 내에 private, protected 는 가져올 수 없다 (캡슐화 파손 방지)

// 또한 확장함수를 만들었는데 클래스 내부에 시그니처 함수와 동일하다면 클래스 내부의 시그니처 함수 (멤버함수) 가 먼저 호출 된다
// 그러므로 확장함수를 만들었더니, 클래스 내부에 시그니처 함수가 동일하게 생기면 오류가 날 수 있으니 주의

// fun 확장하려는 클래스.함수이름(파라미터): 리턴타입 {
// this 를 이용해 실제 클래스 안에 값에 접근한다 (이 때 this 는 수신객체 라고 지칭)
// }
fun String.lastChar(): Char { //-> String 클래스를 확장
    return this[this.length - 1] // this를 통해 String 인스턴스에 접근
}

// 확장 Property -> custom getter 에 확장함수를 섞었다
val String.lastChar: Char
    get() = this[this.length - 1]

fun Person.nextYearAge(): Int { // shadowded 되었다고 경고 발생함 (동일한 멤버 함수가 존재)
    println("확장 함수")
    return this.age + 1
}

// 오버라이드 되는 경우는?
// 선언하여 리턴 타입으로 나뉘어 지는데, 항상 상위 클래스의 확장함수가 호출된다
open class Train(val name: String = "기차", val cost: Int = 5_000)

fun Train.isExpensive(): Boolean {
    return cost >= 10000
}

class Srt : Train(name = "SRT", cost = 4_000)

fun Srt.isExpensive(): Boolean {
    return cost >= 5000
}

// infix
// 변수.함수이름(argument) 대신 변수 함수이름 argument ( ex) downTo, step...)
infix fun Int.add2(other: Int): Int {
    return this + other
}

// inline
// 함수가 호출되어 그 부분에 더해지는게 아니라
// 함수의 더해지는 로직이 main 안으로 불러들어와짐
// 오버헤드를 줄이기 위해 사용되나 성능 측정이 필수
inline fun Int.add3(other: Int): Int {
    return this + other
}

// 지역 함수
fun createPerson(firstName: String, lastName: String): Person {
    // 아래 결과는 중첩되네?
    if (firstName.isEmpty()) {
        throw IllegalArgumentException("firstNane은 비어있을 수 없습니다! 현재 값 : $firstName")
    }

    if (lastName.isEmpty()) {
        throw IllegalArgumentException("LastName은 비어있을 수 없습니다! 현재 값 : $lastName")
    }

    return Person(firstName, lastName, 1)
}

fun createPerson2(firstName: String, lastName: String): Person {
    //이렇게 대체 됨
    fun validateName(name: String, fieldName: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("${fieldName}은 비어있을 수 없습니다! 현재 값 : $name")
        }
    }

    validateName(firstName, "firstName")
    validateName(lastName, "lastName")

    return Person(firstName, lastName, 1)
}

fun main() {
    //region "확장함수"
    val strings = "ABCD"
    println(strings.lastChar())

    //동일한 시그니처 함수 존재시 멤버 함수가 항상 호출됨 유의
    val person = Person("KYJ", "K", 1)
    person.nextYearAge()

    // train 의 isExpensive
    val train: Train = Train()
    train.isExpensive()

    // train 의 isExpensive
    var srt: Train = Train()
    srt.isExpensive()

    // srt 의 is Expensive
    var train2: Srt = Srt()
    train2.isExpensive()
    //endregion

    //region "infix 중위 함수"
    println(10 add2 10)
    //endregion

    //region "inline"
    //함수가 호출되어 그 부분에 더해지는게 아니라
    //함수의 더해지는 로직이 main 안으로 불러들어와짐
    //오버헤드를 줄이기 위해 사용되나 성능 측정이 필수
    println(10.add3(10))
    //endregion
}