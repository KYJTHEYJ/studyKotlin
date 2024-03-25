package kotlinPackage.lec08

//block 사용시 Unit이 반환되지 않는다면 무조건 타입 명시 필요함
fun max(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

//완전한 축약 가능
fun max2(a: Int, b: Int) = if (a > b) a else b

//만약 OverLoading 을 한다면 아래 Method 는 3개의 Method 로 만들어 지게 될 것
fun repeat(str: String, num: Int = 3, useNewLine: Boolean = true) {
    for(i in 1..num) {
        if(useNewLine)
            println(str)
        else
            print(str)
    }
}

//가변인자는 varang으로 써야함 Java 에선 ...
fun printAll(vararg strings: String) {
    for(string in strings) {
        println(string)
    }
}

//하지만 코틀린은 default Parameter 를 위처럼 지정 가능해서
fun main() {
    repeat("default Parameter!") // 기본값이 지정된 Parameter 들 때문에 하나만 지정해도 가능

    //Parameter에 이름을 지정해주는게 가능
    repeat("default Parameter!", useNewLine = false) // named Parameter

    println("\n-----")

    //배열을 가변인자로 사용할 경우에는 앞에 * 연산자 필요 (* -> Spread)
    val arrayStrings: Array<String> = arrayOf("a", "b", "c")

    printAll(*arrayStrings)
}