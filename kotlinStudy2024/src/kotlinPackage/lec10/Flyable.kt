package kotlinPackage.lec10

interface Flyable {

    // 굳이 default 없어도 구현 메소드를 Interface 안에 가능
    fun act() {
        println("날기")
    }
}