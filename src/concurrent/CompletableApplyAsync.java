package concurrent;// concurrent/CompletableApplyAsync.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableApplyAsync {
  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    CompletableFuture<Integer> cf =CompletableFuture.supplyAsync(() -> {
              System.out.println("当前线程：" + Thread.currentThread().getId());
              int i = 10 / 2;
              System.out.println("运行结果：" + i);
              return i;
            },executor);
    System.out.println(cf.join());
  }
}
/* Output:
103
Machina0: ONE
Machina0: TWO
Machina0: THREE
Machina0: complete
Machina0: complete
545
*/
