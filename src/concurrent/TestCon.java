package concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * @Author pengwen
 * @DESCRIPTION
 * @Create 2023/3/16
 */
public class TestCon {
    public static CompletableFuture<TestPizza> makeCF(TestPizza za) {
        return CompletableFuture.completedFuture(za)
                .thenApply(TestPizza::roll)
                .thenApply(TestPizza::sauce)
                .thenApply(TestPizza::box);
    }
    public static CompletableFuture<TestPizza> makeCF2(TestPizza za) {
        return CompletableFuture.completedFuture(za)
                .thenApplyAsync(TestPizza::roll)
                .thenApplyAsync(TestPizza::sauce)
                .thenApplyAsync(TestPizza::box);
    }
    public static void main(String[] args) {
        makeCF(new TestPizza());
        System.out.println("==========");
        CompletableFuture<TestPizza> future = makeCF2(new TestPizza());
        System.out.println(future.join());
    }
}
