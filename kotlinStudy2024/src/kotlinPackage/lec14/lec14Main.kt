package kotlinPackage.lec14

class lec14Main {

}

// Data class
// DTO는 기본적으로 데이터, 생성자, getter, equals, hashCode, toString.. 등등이 있다
// lombok을 쓰는 등으로 코드의 양을 줄이고 있다 (Java)

// 코틀린은 앞에 data class 라고 붙여주면 자동으로 생성함
data class PersonDto(val name: String, val age: Int = 1000) {

}

//enum Class 의 선언을 아래와 같이하면, code에 대해 Getter 및 value를 찾을 수 있다
enum class Contury(
    val code: String
) {
    KOREA("KR"), AMERICA("US")
    ;
}

//when 과 더해지면 자바에서는 if-else 를 대체해 더 간결하게 코드를 구성할 수 있다
//또한 enum class 에 항목이 추가되었을 때 when 구절에도 추가를 바란다는 Warning 을 띄워준다
//컴파일러가 Country 의 모든 타입을 가져간 상태라서 else 도 필요가 없다
fun handleContury(country: Contury) {
    when (country) {
        Contury.KOREA -> TODO()
        Contury.AMERICA -> TODO()
    }
}

//상속이 가능한 추상클래스는 만들까 하는데 외부에서는 이 클래스를 상속 받지 못하게 할 때
//sealed class 를 사용한다

//컴파일 타임 때 하위 클래스의 타입을 모두 기억함 ** (enum 과 비슷) 그러므로 when 활용 가능
//런타임 때 클래스 타입이 추가 될수가 없음 **
//하위 클래스는 같은 패키지에 있어야함
//enum과 다른 점은 클래스의 상속이 가능하고 하위 클래스의 인스턴스화가 가능
sealed class Car(
    val year: Int,
    val cost: Int
)

class Mohave : Car(2021, 1_000)
class Grandeur : Car(2024, 4_000)
class Genesis : Car(2022, 3_000)

private fun handleCar(car: Car) {
    when (car) {
        is Mohave -> TODO()
        is Genesis -> TODO()
        is Grandeur -> println("${car.year} / ${car.cost}")
    }
}


fun main() {
    val dto = PersonDto("KKK", 100)
    val dto2 = PersonDto("YYY", 200)
    val dto3 = PersonDto("JJJ")

    //getter
    dto.name
    dto.age

    //equals
    println(dto == dto2)

    //toString
    println(dto)

    //hashCode
    println(dto.hashCode())

    //Builder Pattern 까지 named Parameter를 활용하면 누릴 수 있다
    println(dto3.name + " / " + dto3.age)

    // -----

    handleCar(Grandeur())
}