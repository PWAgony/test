public class Wildcards {
  static class Holder<T> {
    private T value;
    public Holder() {
    }
    public Holder(T val) {
      value = val;
    }
    public void set(T val) {
      value = val;
    }
    public T get() {
      return value;
    }
  }
  static void rawArgs(Holder holder, Object arg) {
    holder.set(arg);
    Object obj = holder.get();
  }
  static void unboundedArg(Holder<?> holder, Object arg) {
    //holder.set(arg);//编译错误
    // Can't do this; don't have any 'T':
    // T t = holder.get();
    Object obj = holder.get();
  }
  static <T> T exact1(Holder<T> holder) {
    return holder.get();
  }
  static <T> T exact2(Holder<T> holder, T arg) {
    holder.set(arg);
    return holder.get();
  }
  static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
    //holder.set(arg);//编译错误
    return holder.get();
  }
  static <T> void wildSupertype(Holder<? super T> holder, T arg) {
    holder.set(arg);
    //T t = holder.get();//编译错误
    Object obj = holder.get();
  }
  public static void main(String[] args) {
    Holder raw = new Holder<>();
    // Or:
    raw = new Holder();
    Holder<Long> qualified = new Holder<>();
    Holder<?> unbounded = new Holder<>();
    Holder<? extends Long> bounded = new Holder<>();
    Long lng = 1L;

    rawArgs(raw, lng);
    rawArgs(qualified, lng);
    rawArgs(unbounded, lng);
    rawArgs(bounded, lng);

    unboundedArg(raw, lng);
    unboundedArg(qualified, lng);
    unboundedArg(unbounded, lng);
    unboundedArg(bounded, lng);

    Object r1 = exact1(raw);
    Long r2 = exact1(qualified);
    Object r3 = exact1(unbounded); // Must return Object
    Long r4 = exact1(bounded);

    Long r5 = exact2(raw, lng);
    Long r6 = exact2(qualified, lng);
    //Long r7 = exact2(unbounded, lng);//编译错误
    //Long r8 = exact2(bounded, lng);//编译错误

    Long r9 = wildSubtype(raw, lng);
    Long r10 = wildSubtype(qualified, lng);
    // OK, but can only return Object:
    Object r11 = wildSubtype(unbounded, lng);
    Long r12 = wildSubtype(bounded, lng);

    wildSupertype(raw, lng);
    wildSupertype(qualified, lng);
   // wildSupertype(unbounded, lng);//编译错误
    //wildSupertype(bounded, lng);//编译错误
  }
}
