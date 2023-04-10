import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/1
 */
public class FanXing {
    //T 可以出现在方法的三个地方 方法入参，方法体，返回值
    //这三个地方任何一个地方出现了占位符 就代表他是一个泛型方法

    //第一种情况：入参有 T，方法体和返回值没有 T，也就是说，T填充符仅仅出现在入参上。
    public static <T> void fn(List<T> list) {
        System.out.println(list.size());
    }
    //我们使用 T 的目的，就是为了在方法体或者返回值中捕获这个填充的T然后使用它
    //但是你没有使用这样的一种思路 你这个泛型方法就毫无意义 所以最好改成下面非泛型方法
    public static void fn1(List<?> list) {
        System.out.println(list.size());
    }

    //第二种情况：T只出现在方法体中，入参和返回值都没有 T
    public static <T> int fn2() {
        //JDK 5 之前，用 List list = new ArrayList()
        // 但是，JDK 5 之后，我们必须要用 尖括号 不然有警告
        //List<T> list = new ArrayList<T>();  跟第一种情况一样
        List<?> list = new ArrayList<>();
        return list.size();
    }

    //第三种情况：返回值中有T，方法入参和方法体都没有T 这种情况不存在
    //public static <T> T fn3() {
        //return 10;
    //}

    //第四种情况：入参没有T，方法体和返回值有T
    //在你调用这个方法的时候，用引用接收的时候，指定T
    //List<String> list = fn4(); 这个 T，是在返回值引用中指定填充的
    public static <T> List<T> fn4() {
        List<T> list = new ArrayList<>();
        return list;
    }

    //第五种情况 方法入参有T，返回值有T，方法体没有T
    public static <T> T fn5(List<T> list) {
        /**
         * 等价于T t = list.get(0); return t;
         * 返回值就是方法体的一部分，只要返回值中有T，方法体中一定使用了T
         */
        return list.get(0);
    }

    //第六种情况 入参有T，方法体有 T，但是返回值没有T
    //在代码中用 Object,破坏了语义,让语义不明确了,而这里用 T,语义是非常明确的
    //所以这里入参不能用List<?> list，但是List <?> list = new ArrayList<>(); fn6(list)
    //填充任何东西都不会影响方法的运行,这个方法的填充T,没什么运行的意义,只是让代码更清晰一点
    //<R, A> R collect(Collector<? super T, A, R> collector); list.stream().collect(Collectors.toList());
    //  Stream 中，T 是已知的,入参有 R 和 A,返回值，只有 R，没有 A, 这里就不在乎你这个A到底是什么
    //那么public static <T> Collector<T, ?, List<T>> toList(){} A的部分就是？
    public static <T> int fn6(List<T> list) {
        T t1 = list.get(0);
        Map<String, T> map = new HashMap<>();
        map.put("a", t1);
        return map.size();
    }

    //第七种情况 方法入参，方法体，返回值，都有T 最常见的
    public static <T> T fn7(List<T> list) {
        T t = list.get(0);
        return t;
    }
}
