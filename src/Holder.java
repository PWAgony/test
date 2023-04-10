// generics/Holder.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Holder<T> {
  private T value;
  public Holder() {}
  public Holder(T val) { value = val; }
  public void set(T val) { value = val; }
  public T get() { return value; }
  @Override public boolean equals(Object o) {
    return o instanceof Holder &&
      Objects.equals(value, ((Holder)o).value);
  }
  @Override public int hashCode() {
    return Objects.hashCode(value);
  }
  static void writeTo(List<? super Apple> apples) {
    apples.add(new Apple());
    apples.add(new Jonathan());
// apples.add(new Fruit()); // Error
  }
  public static void addNumbers(List<?> list) {
    //编译错误
    //list.add(new Object());
    list.add(null);
    Object o = list.get(0);//get出来以后只能用Object接收
  }
  public static void addNumbers2(List<? super Fruit> list) {
    list.add(new Fruit());
    list.add(new Apple());
    //list.add(new Object());//编译错误
  }
  public static void main(String[] args) {
    List<Apple> list = new ArrayList<>();
    addNumbers(list);
    List<Fruit> list2 = new ArrayList<>();
    addNumbers(list2);

    List<Fruit> list3 = new ArrayList<>();
    addNumbers2(list3);
    List<Apple> list4 = new ArrayList<>();
    //addNumbers2(list4);//编译错误
    List<Object> list5 = new ArrayList<>();
    addNumbers2(list5);
  }
}
/* Output:
java.lang.ClassCastException: Apple cannot be cast to
Orange
false
*/
