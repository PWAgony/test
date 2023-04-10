package concurrent;

import java.util.concurrent.*;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/6
 */
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        System.out.println("main.................start.....");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor);
        System.out.println("main.................end....." + completableFuture.join());



    }
}
