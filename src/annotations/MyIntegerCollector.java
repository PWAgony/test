package annotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/2
 * 收集这个整数流，然后呢，计算统计其中的小于 100，大于 50 的整数的个数，并且，输出所有的整数
 *  这里的Collector不关心这种业务，真正做业务的是 Stream 的 collect 方法
 * 收集 Integer，使用的内部累计容器是一个 List<Integer>,最后的结果 Result
 */
public class MyIntegerCollector implements Collector<Integer, List<Integer>, Result> {

    //把收集器的内部累加容器交给 Stream 的 collect 方法
    @Override
    public Supplier<List<Integer>> supplier() {
        return () -> new ArrayList<Integer>();
    }

    //定义你收集的业务
    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return (list, i) -> {
            if (i < 100 && i > 50) {
                list.add(i);
            }
        };
    }

    //通过容器返回结果
    @Override
    public Function<List<Integer>, Result> finisher() {
        return list -> {
            Result result = new Result();
            result.setCount(list.size());
            result.setList(list);
            return result;
        };
    }

    //steam使用了并发操作(parallel)时就必须进行对象合并
    //并行操作，会把一个流分成若干份，并行收集, 然后最后汇总在一起
    //如果你使用了并行流,那么，你这个 collect 收集方法是并行的，每一个并行的线程中,都有一个独立的 A
    //累加容器最后汇总的时候需要把并行累计的 A 汇总成一个大的 A,所以，这一步，是为了并行流准备的
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
    //CONCURRENT——accumulator 函数可以从多个线程同时调用，且该收集器可以并行归约流。
    //如果收集器没有标为 UNORDERED，那它仅在用于无序数据源时才可以并行归约
    //IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况
    //下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检查地转
    //换为结果 R 是安全的(说明 A 不需要转换成 R 这一步了,这个时候 A 和 R 的泛型是一样的)
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> set = new HashSet<>();
        set.add(Characteristics.UNORDERED);
        set.add(Characteristics.CONCURRENT);//如果我们需要支持并行流收集 必须把这个加上
        return set;
    }

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(150, 160, 60, 10, 80);
        Result result = stream.collect(new MyIntegerCollector());
        stream.collect(Collectors.toList());
        System.out.println(result);
    }
}
