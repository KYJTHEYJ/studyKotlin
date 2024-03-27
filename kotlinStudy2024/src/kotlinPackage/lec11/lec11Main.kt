package kotlinPackage.lec11

// public : 동일

// protected : 선언된 클래스와 하위클래스에서만 사용 (패키지가 namespace 관리로만 쓰임), 파일에는 사용 불가
// -> Java 는 같은 패키지 또는 하위 클래스에서만 접근 가능

// internal : 같은 모듈에서만 접근가능 -> 라이브러리 구성시 작동을 위해서 내부에서만 돌아가게 할 때 사용한다.
// -> Java 는 default 로 같은 패키지에서만 접근 가능

// module -> 한 번에 같이 컴파일 되는 Kotlin code 들
// 일반적으로 관련된 기능, 클래스, 또는 파일의 논리적인 집합
// ex) IDEA module, gradle source set, maven project ...

// private : 동일

// --------------------------------

// 불가 : protected val test: Int = 10

// 생성자에 사용할 때는 constructor 키워드 생략 불가
class cat private constructor(leg: Int) {

}

class cat2 internal constructor(leg: Int) {

}

//자기 자신과 하위만 쓰이니 open을 해라 (final 키워드가 자동으로 붙기 때문)
open class cat3 protected constructor(leg: Int) {

}

class Car(
    internal val name: String, // internal 로 getter, setter 기록됨
    private var owner: String, // private 로 getter, setter 기록됨
    _price: Int
) {
    var price = _price
        private set // setter 에만 private 부여
        //private get // get은 따로 지정 못하는게, 프로퍼티와 동일해야함
}

fun main() {
    // 유틸성 코드
    isDirectoryPath("test/");

    val car: Car = Car("GTS", "KYJ", 10000)
}

