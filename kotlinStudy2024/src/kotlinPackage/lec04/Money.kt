package kotlinPackage.lec04

data class Money (
    val amount: Long
){
    // operator
    // operator 키워드는 특정 함수를 해당 클래스의 연산자로 오버로딩(overloading)하는 데 사용됩니다.
    // 이를 통해 기본적인 연산자(+, -, *, /, +=, -= 등)를 해당 클래스 인스턴스에 대해 사용자 정의 방식으로 작동하게 할 수 있습니다.
    // 즉, 클래스 인스턴스 간에 연산자를 사용한 연산을 정의할 수 있게 해줍니다.

    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }
}