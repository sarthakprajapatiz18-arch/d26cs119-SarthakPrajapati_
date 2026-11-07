package service;
// Purpose: Manages asynchronous tasks using a fixed-size executor service.
import java.util.concurrent.*;
public class TransactionProcessor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    public void submit(Runnable task) { pool.execute(task); }
    public void stop() {
        pool.shutdown();
        try { if (!pool.awaitTermination(30, TimeUnit.SECONDS)) pool.shutdownNow(); }
        catch (InterruptedException e) { pool.shutdownNow(); Thread.currentThread().interrupt(); }
    }
}
