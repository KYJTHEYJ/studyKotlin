package kotlinPackage.lec09

// 생성자 -> constructor
// field 를 생성하면 자동으로 property 들을 생성함
class Person constructor(name: String = "김기본", val age: Int = 100) { // 주생성자 (필수)

    // backing field
    // field 가 아니라 name 을 써버리면 무슨 일이?
    // name 을 호출하게 되면 name 에 대한 getter 를 호출함
    // 근데 getter 안에는 name 이 있어 무한 반복됨
    // 그러므로 이를 방지하기 위해 field 라는 자기 자신을 가르키는 예약어로 호출해야함
    // 근데 이런식으로 custom getter 에서는 자주 안씀 ***
    val name: String = name
        get() = field.uppercase()

    // 굳이 backing field 를 안 쓰면
    val uppercaseName: String
        get() = this.name.uppercase()

    // 그런데.. custom getter를 하기보다는 아래 처럼 쓰는게 낫다
    fun getUpperCaseName(): String {
        return this.name.uppercase()
    }

    // custom setter 는 set() 으로 하면되나
    // setter 자체가 지양 ***
    //var name: String = name
    //    set(value) {field = value.uppercase()}

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


    // 성인인지 검사하려면

    // 함수로 선언하는 법
    /*
    fun isAdult() : Boolean {
        return this.age >= 20
    }
    */

    // 성인인지 검사하려면 2 (Custom Getter)
    val isAdult: Boolean
        get() = this.age >= 20

    /*
    // 위 코드와 같은 코드
    val isAdult: Boolean
    get() {
        return this.age >= 20
    }
    */

}

//위 함수와 동일하다
class Person2(val name: String, var age: Int)

class Person3(val birthYear: Int) {
    private fun calculateAge(currentYear: Int): Int {
        return currentYear - birthYear
    }

    fun isAdult(currentYear: Int): Boolean {
        return calculateAge(currentYear) >= 20
    }
}

class Person4(
    name: String = "kimnolife",
    age: Int = 100
) {
    init {
        if(age <= 0)
            throw Exception("나이는 ${age}일 수 없습니다")
    }

    val name: String = name
        get() = field.uppercase()
}

/*
    Kotlin 에서 클래스의 생성자에 사용되는 파라미터에 val이나 var을 붙이지 않는 경우
    , 그 파라미터는 단순히 생성자 내에서만 사용되는 임시 변수로 취급됩니다.

    즉, 그 파라미터는 클래스의 프로퍼티로 저장되지 않고,
    클래스 내부에서 직접적으로 접근할 수 있는 필드가 되지 않습니다.
 */
class Person99(firstName: String, age: Int) {
    val firstName: String
    var age: Int

    // 초기화 까지 해주어야 클래스의 프로퍼티가 됨 (애초에 초기화 안할 순 없음)
    // init 에서 이렇게 해주던 값을 넣든 해야함
    init {
        this.firstName = firstName
        this.age = age
    }
}

class Person999(val firstName: String, var age: Int)

class Person9999(val firstName: String, val lastName: String, age: Int) {
    var age: Int = age
        private set  // 외부에서 변경 불가, 내부에서만 변경 가능

    // 다른 로직 필요 시 사용
    fun haveBirthday() {
        age += 1
    }
}
fun main() {
    val person: Person = Person("KYJ", 30)

    // Getter
    val name: String = person.name
    var age: Int = person.age

    // 실행순서 확인
    Person()

    println("-------\n")

    val person1 = Person("홍길동", 30)
    val person2 = Person("이순신", 15)
    val person3 = Person("유관순")
    val person4 = Person4("kim_useless", 8)

    println("${person1.name}은 성인인가요? ${person1.isAdult}")  // 홍길동은 성인인가요? true
    println("${person2.name}은 성인인가요? ${person2.isAdult}")  // 이순신은 성인인가요? false
    println("${person3.name}은 성인인가요? ${person3.isAdult}")  // 유관순은 성인인가요? false

    val person99 = Person99("John", 25)
    println(person99.firstName)  // John
    println(person99.age)        // 25

    val person9999 = Person9999("John", "Doe", 25)
    //person9999.age = 899; //-> setter is private
}