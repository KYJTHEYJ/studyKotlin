package kotlinPackage.lec13

import javaPackage.lec13.JavaHouse.LivingRoom

fun main() {

}

// 클래스 안에 내부 클래스는 static 으로 선언해서 사용할 것을 추천한다
// (내부 클래스가 외부 클래스의 정보를 참조 가능한데, 참조를 해제하지 않는 이상
// 이 때문에 메모리 누수가 나며, 디버깅이 어렵기 때문)

// static 으로 선언시 외부 정보를 참조할 수는 없다
// 코틀린은 이 가이드를 충분히 이행 중이다
class House (
    val adress: String,
    val livingRoom: LivingRoom
) {
    class LivingRoom ( // -> 자동으로 static
        private var area: Double
    )

    inner class LivingRoom2 ( // -> 외부 클래스 참조가 가능한 권장되지 않는 클래스
        private val area: Double
    ) {
        val adress:String
            get() = this@House.adress // -> 외부 클래스의 adress를 custom get
    }
}

class lec13Main {

}