package kotlinPackage.lec11

//Java 에서 class 를 abstract 로 놓고 생성자를 private 로 놓아 유틸성 코드를 남겨놓고 싶을 때..
//(상속받게도 못하고 인스턴스화도 못하고 그냥 유틸성 func 을 남기는 클래스로 남기고 싶을 때)

//코틀린도 위 방법이 가능은 하나, 그냥 파일 최상단에 위치시키면 된다 (정적인 개념으로 넘어감)
fun isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}