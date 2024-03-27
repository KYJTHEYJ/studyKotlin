package kotlinPackage.lec10

interface Swimable {

    val swimAbility: Int

    // 굳이 default 없어도 구현 메소드를 Interface 안에 가능
    fun act() {
        println("수영")
    }
}