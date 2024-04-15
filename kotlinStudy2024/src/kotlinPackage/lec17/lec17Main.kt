package kotlinPackage.lec17

import javaPackage.lec17.Fruit

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("바나나", 2500),
        Fruit("수박", 10000)
    )

    //fun 뒤에 이름이 없음 (바로 파라미터와 리턴타입 명시), 람다 생성법 1
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }
    // isApple의 반환형은 (Fruit) -> Boolean

    //중괄호 이후 파라미터와 리턴타입 명시 후 -> 사용, 람다 생성법 2 *** 이게 좀더 많이 사용
    val isApple2 = { fruit: Fruit -> fruit.name == "사과" }

    //region "다양한 컬렉션 처리 기능"
    //all : 조건을 모두 만족시 true, 아니면 false
    val isAllApple = fruits.all { fruit: Fruit -> fruit.name == "사과" }

    //none : all 의 반대
    val isNoneApple = fruits.none { fruit: Fruit -> fruit.name == "사과" }

    //any : 하나라도 만족시 true, 아니면 false
    val isInApple = fruits.any { fruit: Fruit -> fruit.name == "사과" }

    //count : 갯수 세기
    val fruitCount = fruits.count()

    //sortedBy, sortedByDescending : 오름차순, 내림차순
    val fruitSorted = fruits.sortedBy { fruit: Fruit -> fruit.price }
    val fruitSorted2 = fruits.sortedByDescending { fruit: Fruit -> fruit.price }

    //distinctBy : 중복 제거
    //map : 컬렉션의 원하는 요소만 가지고 다시 컬렉션을 생성함
    val fruitDistinct = fruits.distinctBy { fruit: Fruit -> fruit.name }.map { fruit: Fruit -> fruit.name }

    //first, last : 첫번째나 마지막 값을 가져옴 (null 시 에러)
    //firstOrNull, lastOrNull : 첫번째나 마지막 값을 가져오거나 null
    //endregion

    //region "List -> Map"
    // 과일이름, 과일리스트 (과일이름에 해당하는 것들로) 의 형태의 Map으로 분리시
    val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }

    // id 처리가 필요할 때, 중복되지 않는 키로 매핑해야할 때
    //val map2: Map<Long, Fruit> = fruits.associate { fruit -> fruit.id }

    // 과일이름, 출고가 리스트가 필요할 때
    val map3: Map<String, List<Int>> = fruits.groupBy({ fruit -> fruit.name }, { fruit -> fruit.price })

    // 과일이름, 출고가 리스트가 필요하고 사과만 볼 때
    // 앞의 기능들도 붙여 이용 가능
    val map4: Map<String, List<Int>> =
        fruits.groupBy({ fruit -> fruit.name }, { fruit -> fruit.price })
            .filter { (key, value) -> key == "사과" }

    //endregion

    //region "중첩 컬렉션"
    // 만약 컬렉션 안에 컬렉션이 중첩되어 있다고 치자
    // List<List<Fruit>> 과 같은 상황 일 때 사용

    // 그냥 List<Fruit> 의 형태로 바꿀 때 (단순 평탄화)
    // .flatten 사용

    // 또는 flatMap 을 사용 한다
    // 여기서 filter 를 추가하던, 요소에 함수를 적용한 후 평탄화 함

    val nestedList = listOf(1, 2, 3)
    val result = nestedList.flatMap { listOf(it, it*2, it*3) }
    //endregion

    //람다 직접 호출법들
    isApple(fruits[0])
    isApple.invoke(fruits[0])

    filterFruits(fruits, isApple)

    // 익명함수를 직접 파라미터에 넣어도 됨
    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과" })

    // 그런데 중괄호 구문을 파라미터로 주면 코틀린에서 밖으로 빼내려함
    // 마지막으로 주는 중괄호 구문을 밖으로 빼면, 마지막에 명시된 파라미터로 인식됨
    filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과" }

    // 람다를 여러줄 작성 가능한데, 마지막 줄이 return 된다
    filterFruits(fruits) { fruit: Fruit ->
        println("사과만 받자!")
        fruit.name == "사과"
    }

    // 그런데 우리는 filterFruits 함수를 통해 fruit 의 type 추론이 가능함
    filterFruits(fruits) { fruit -> fruit.name == "사과" }

    // 익명함수가 파라미터를 하나만 지녔을 경우 코틀린만의 it 문법으로 한번더 축약 가능 (전단계 까지만 많이 쓰임)
    filterFruits(fruits) { it.name == "사과" }

    // 자바에서는 람다 내에 변수를 사용시, final 로 지칭된 부분만 사용 가능함
    // 그런데 코틀린은 아무 제약이 없음
    // 람다가 사용되는 시점에 사용되는 변수들을 포획하여 사용하기 때문
    // 이런 데이터 구조를 Closure 라고 함 **
    var targetFruit = "바나나"
    targetFruit = "수박"

    filterFruits(fruits) { it.name == targetFruit }
}

private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean): List<Fruit> {
    /*
    val results = mutableListOf<Fruit>()

    for(fruit in fruits) {
        if(filter(fruit)) {
            results.add(fruit)
        }
    }

    return results
    */

    // 함수형 프로그래밍을 거치면 아래와 같이 변환
    return fruits.filter(filter)
}


