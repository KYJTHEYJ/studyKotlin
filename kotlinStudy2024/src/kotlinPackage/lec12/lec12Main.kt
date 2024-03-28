package kotlinPackage.lec12

import javaPackage.lec12.Movable

//lec12 JavaPerson 참조
class Person private constructor(
    var name: String,
    var age: Int
) {
    companion object : Log { // Java 의 static 을 대체 하는 키워드 companion object
        // Java 와 차이는 이름을 붙일 수 있고 interface 를 가져와 붙일 수 있다는 점
        private const val MIN_AGE = 1 // const 를 붙이지 않으면 런타임 시 변수가 할당, const 를 붙이면 컴파일 시 변수가 할당

        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("person 클래스의 동행 객체")
        }
    }

    // static : 클래스가 인스턴스 화 될 때, 새로운 값이 복제되는게 아니라 정적으로 인스턴스 끼리 값을 공유
    // companion object : 클래스와 동행하는 유일한 오브젝트 (인스턴스가 여러 개 생기더라도 값은 공유)

    // Java 에서 사용시, Person.Companion.~~ (이름을 붙이지 않았을 때)
    // Person.이름.~~ (이름을 붙였을 때)
    // Person.@JvmStatic 이 붙은 메소드 이름 (companion object 내부 메소드에 @JvmStatic 어노테이션 사용시)
}

// Singleton (하나의 인스턴스만 갖는 클래스)
// 싱글톤으로 유틸성 코드를 작성하는 것보다 그냥 파일 최상단을 쓰는게 낫다
object Singleton // Java 의 싱글톤 패턴을 한 코드로 대체 가능 (이 자체가 하나의 클래스)
{
    var a: Int = 0;
}

// 익명 클래스 : 특정 인터페이스 나 클래스를 상속받은 구현체를 일회성으로 사용할 때 쓰는 클래스
// object : 타입 의 형태로 서술
private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}

fun main() {
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)

    // Java 와 다르게 object 형으로 Movable을 당겨 받고 내부에 implement 함
    moveSomething(object : Movable {
        override fun move() {
            println("move")
        }

        override fun fly() {
            println("fly")
        }
    })
}

