package javaPackage.lec04;

public class Lec04Main {

  public static void main(String[] args) {
    JavaMoney money1 = new JavaMoney(1_000L);
    JavaMoney money2 = new JavaMoney(2_000L);
    System.out.println(money1.plus(money2));

    if(money1.compareTo(money2) == 0) {
      System.out.println("객체비교에서 자동으로 CompareTo 호출 x");
    }

    if(money1.equals(money2)) {
      System.out.println("동등성 비교에서도 자동으로 Equals 호출 x");
    }

    // 객체를 더할 때도 연산자 사용 못함
    System.out.println(money1.plus(money2));
  }
}
