package kotlinPackage.lec09

// 생성자 -> constructor
// field 를 생성하면 자동으로 property 들을 생성함
class Person constructor(val name: String, age: Int) { // 주생성자 (필수)
    var age: Int = age

    // backing field
    // field 가 아니라 name 을 써버리면 무슨 일이?
    // name 을 호출하게 되면 name 에 대한 getter 를 호출함
    // 근데 getter 안에는 name 이 있어 무한 반복됨
    // 그러므로 이를 방지하기 위해 field 라는 자기 자신을 가르키는 예약어로 호출해야함
    // 근데 이런식으로 custom getter 에서는 자주 안씀 ***

    // custom setter 는 set() 으로 하면되나
    // setter 자체가 지양 ***

    //val name: String = name
    //    get() = field.uppercase()

    // custom getter를 하기보다는 아래 처럼 쓰는게 낫다
    fun getUpperCaseName(): String {
        return this.name.uppercase()
    }

    init {
        if(age <= 0) {
            throw IllegalArgumentException("나이는 $age 일 수 없습니다")
        }

        println("초기화 블록")
    }

    // 부생성자 , 무조건 this로 주 생성자를 호출하여야함
    // -> 코틀린은 부생성자 보다는 default Parameter 를 권장함 ***
    constructor(name: String): this(name, 1) {
        println("부생성자 1")
    }

    constructor(): this("홍길동") {
        println("부생성자 2")
    }

    @JvmName("isAdult1")
    // 성인인지 검사하려면
    fun isAdult() : Boolean {
        return this.age >= 20
    }

    //동일 age에 접근하는 동일한 시그니처라서 이름 지정 필요 (사실 공부용이라 이렇게..)
    @get:JvmName("isAdult2")
    // 성인인지 검사하려면 2 (Custom Getter)
    val isAdult: Boolean
        get() = this.age >= 20

    @get:JvmName("isAdult3")
    val isAdult2: Boolean
        get() {
            return this.age >= 20
        }

}

//위 함수와 동일하다
class Person2(val name: String, var age: Int)

fun main() {
    val person: Person = Person("KYJ", 30)

    // Getter
    val name: String = person.name
    var age: Int = person.age

    // 실행순서 확인
    Person()
}

/*
    Kotlin 에서 클래스의 생성자에 사용되는 파라미터에 val이나 var을 붙이지 않는 경우
    , 그 파라미터는 단순히 생성자 내에서만 사용되는 임시 변수로 취급됩니다.

    즉, 그 파라미터는 클래스의 프로퍼티로 저장되지 않고,
    클래스 내부에서 직접적으로 접근할 수 있는 필드가 되지 않습니다.
 */