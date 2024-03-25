package kotlinPackage.lec05

fun validateScoreIsNotNegative(score: Int) {

    //Java와 그리 다를게 없다
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }
}

//Java는 statement, 코틀린은 expression (식이냐 값이냐 문제, Java면 3항 연산자, 코틀린은 이대로 사용 가능)
//값이면 식에 대입 가능
fun getPassOrFail(score: Int) {
    if (score > 50) {
        println("pass")
    } else {
        println("non-pass")
    }
}

//expression 이라서 return if ~~ 가능
fun getGrade(score: Int): String {
    return if (score > 90) {
        "A"
    } else {
        "F"
    }
}

fun whatIsIn(score: Int) {
    //if (0 <= score && score <= 100)
    //와 if(scroe in 0..100) 과 동일하다
    //반대는 !in
}

fun whatIsWhen(score: Int) {
    //switch를 when이 대체한다
    when(score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "F"
    }

    //물론 in 을 섞는게 가능하다
    when(score) {
        in 90..100 -> "A"
        else -> "F"
    }
}

//물론 when도 expression
fun startWithA(obj: Any): Boolean {
    return when(obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

//when의 조건부는 값만을 나열해도 된다
//1 or 0 or -1을 판별하는 조건을 when으로
fun judgeNumber(number: Int) {
    when(number) {
        1, 0, -1 -> println("아는 숫자에요")
        else -> println("모르는 숫자에요")
    }
}

//판별 값을 빼면 if 문의 역할도 어느정도 대체 된다
//when이 항상 위에서 아래로 내려오면서 체크하는 것을 의미한다, 결과도 만족한다면 바로 return하고 멈춘다 **
fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("0이에요")
        number % 2  == 0 -> println("짝수")
        else -> println("홀수에요")
    }
}

fun main() {
    var grade: String = getGrade(100)
}