package concurrent;// concurrent/Pizza.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class TestPizza {
  private int i = 1;
  public TestPizza roll() {
    i = i + 1;
    System.out.println("roll:" + i);
    return this;
  }
  public TestPizza sauce() {
    i = i + 2;
    System.out.println("sauce:" + i);
    return this;
  }
  public TestPizza box() {
    i = i + 3;
    System.out.println("box:" + i);
    return this;
  }
}
