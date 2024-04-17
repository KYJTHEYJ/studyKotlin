package kotlinPackage.lec20

import kotlinPackage.lec09.Person

//scope function : 일시적인 영역을 형성하는 함수

//let : 확장함수. 람다를 받아서 람다 결과를 반환한다
fun printPerson(person: Person) {
    person.let {
        println(it.name)
        println(it.age)
    }
}

fun main() {

    val person = Person("KYJ", 100)

    // 람다의 결과를 반환 : let, run
    // let 은 일반 함수를 받아서 처리, it
    val value1 = person.let {
        it.age
    }

    // run 은 확장함수를 받아서 처리, this
    val value2 = person.run {
        this.age
    }

    // 람다의 결과와는 무관, 객체 그 자체를 반환 : also, apply
    val value3 = person.also {
        it.age
    }

    val value4 = person.apply {
        this.age
    }

    // with(파라미터, 람다) : this 를 사용해 접근, this 는 생략 가능
    // with 은 확장함수가 아님
    with(person) {
        println(name)
        println(this.age)
    }

    // it : 생략이 불가능하나 다른 네이밍 가능 (it 대신 a 를 붙인다던지..)
    // this : 생략이 가능하나 다른 네이밍은 불가능

    // 언제 scope function 을 사용하는거지?
    // let : 하나 이상의 함수를 call chain 결과로 호출 할 때
    val strings = listOf("APPLE", "CAR")
    strings.map { it.length }
        .filter { it > 3 }
        .let(::println)

    // non-null 값에 대해서만 code block 을 실행 시킬 때
    val str = "wow hungry"
    val length = str?.let {
        println(it.uppercase())
        it.length
    }

    // 일회성으로 제한된 영역에 지역변수를 만들 때.. 근데 private function 으로 따로 빼는게 낫다
    val numbers = listOf("one", "two", "three", "four")
    val modifiedFirstItem = numbers.first()
        .let { firstItem ->
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
        }.uppercase()
    println(modifiedFirstItem)


    // run : 객체 초기화와 반환 값의 계산을 동시에
    // val person = Person("KYJ", 100).run(personRepository::save)

    // apply : 객체 그 자체가 반환됨
    // 객체 설정을 할 때에 객체를 수정하는 로직이 call chain 중간에 필요할 때 사용
    // 회원가입 당시엔 이름과 나이만 받고 그 후 페이지에서 취미를 고르는 경우 같은..
    fun createPerson(
        name: String, age: Int, hobby: String,
    ): Person {
        return Person(
            name = name,
            age = age,
        ).apply {
            //this.hobby = hobby
        }
    }

    // also : 객체 그 자체가 반환됨
    // 객체를 수정하는 로직이 call chain 중간에 필요할 때
    mutableListOf("one", "two", "three")
        .also { println("four 추가 이전 지금 값: $it") }
        .add ("four")

    // with : 특정 객체를 다른 객체로 변환해야 하는데, 모듈 간의 의존성에 의해 정적 팩토리 혹은 toClass 함수를 생성하기 어려울 경우, 노션 참조
    /*
    return with(person) {
        PersonDto (
        name = name,
        age = age,
        )
    }
    */

    //scope vs non-scope
    // 1번 코드
    /*
    if (person != null && person.isAdult) {
        view.showPerson(person)
    } else {
        view.showError()
    }

    // 2번 코드
    person?.takeIf { it.isAdult }
        ?.let(view::showPerson)
        ?: view.showError()
    */

    /*
    // 위 '?' 부터 아래로
    '?' (안전 호출 연산자) : null 이 아닐 때만 메서드나 프로퍼티에 접근하게 합니다.
    'takeIf' : 주어진 조건이 true 일 때 객체를 반환하고, 그렇지 않으면 null 을 반환합니다.
    '?.let' : 객체가 null 이 아닐 경우에 주어진 람다를 실행합니다.
    '?:' (엘비스 연산자) : 왼쪽 표현식이 null 이면 오른쪽 값을 결과로 합니다.
     */

    // 생각보다 복잡성이 커질 수 있어 scope function 을 굳이 남발할 필요는 없다
    // 필요성에 맞추어, 숙련도에 맞추어 clean code 를 작성해 주는 좋은 도구이니 좋게 활용하자.
}