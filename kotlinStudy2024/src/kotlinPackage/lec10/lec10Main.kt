package kotlinPackage.lec10

// 추상 클래스
abstract class Animal(
    protected val species: String,
    protected open val legCount: Int // open 이유는 하단에 서술
) {
    abstract fun move()
}

//변수에 붙일때 변수: 반환값, 클래스 : 상속받을 클래스(생성자 필드들) ** 생성자는 무조건 호출해야됨
class Cat(species: String) : Animal(species, 4) {
    //override 라는 어노테이션이 아닌 지시어로 해결해야함
    override fun move() {
        println("고양이가 걸어간다")
    }
}

class Penguin(species: String) : Animal(species, 2), Swimable, Flyable {

    private val wingCount = 2

    override fun move() {
        println("펭귄이 걸어간다.")
    }

    // custom getter 를 위해 legCount를 super 키워드로 접근해 가져오려면
    // 상속할 클래스의 프로퍼티가 추상이 아닌 이상 open 이라는 키워드를 붙여주어야 함 **
    override val legCount: Int
        get() = super.legCount + this.wingCount

    //중복되는 인터페이스를 특정할 때는 super<명칭할 Interface>.Interface 안 method
    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }

    // 인터페이스 클래스의 프로퍼티도 상속받은 클래스에서 맘대로 사용 가능
    override val swimAbility: Int
        get() = 3

    //그렇다고 인터페이스 클래스를 자바에서처럼 인스턴스 화 불가능 한건 똑같음
}

open class Base (
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)
    }
}

class Drived(override val number: Int) : Base(number) {
    init {
        println("Drived Class")
    }
}

// 상위 클래스가 생성될 동안 하위 클래스의 number 에 값을 넣는 다는 건데,
// 이 상위 클래스의 constructor 되는 동안에 하위 클래스의 property 에 접근시
// 초기화가 이루어지지 않아서 출력시 0으로 나옴 **

// 상위 클래스 설계시, 생성자, 초기화 블록에 사용되는 프로퍼티에는 open 을 무조건 피해야함 ***
fun main() {
    Drived(1000)
}

// 상속 관련 키워드
// final : override 할 수 없게함 (default 로 보이지 않게 존재함)
// open : override 를 열어줌
// abstract : 반드시 override