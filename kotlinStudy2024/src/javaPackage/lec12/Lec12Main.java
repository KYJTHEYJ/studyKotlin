package javaPackage.lec12;

public class Lec12Main {

  private static void moveSometing(Movable movable) {
    movable.move();
    movable.fly();
  }

  public static void main(String[] args) {
    // Person.Factory.newBaby("ABC");

    moveSometing(new Movable() {
      @Override
      public void move() {
        System.out.println("move");
      }

      @Override
      public void fly() {
        System.out.println("fly");
      }
    });
  }

}
