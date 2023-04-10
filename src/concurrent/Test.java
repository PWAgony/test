package concurrent;

import java.util.concurrent.*;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/6
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 2; System.out.println("任务2运行结果：" + i);
            return i;
        }, executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            System.out.println("任务2运行结果：");
            return "hello";
        }, executor);
        //thenCombine：消费任务一和任务二的结果+返回结果，thenAccept:上面任务执行完执行+获取返回值
        CompletableFuture<Void> thenCombineAsync = future1.thenCombineAsync(future2, (result1, result2) -> {
            System.out.println("任务5启动。。。结果1：" + result1 + "。。。结果2：" + result2);
            return result2 + "-->" + result1;
        }, executor).thenAcceptAsync(result -> {
            System.out.println("任务三：" + result);
        }, executor);
        System.out.println("任务5结果" + thenCombineAsync.get());//CompletableFuture<Void> get()就为null
    }
}
