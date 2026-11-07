class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void incrementSafe() {
        count++;
    }
}

public class CounterRace {
    static final int THREADS = 10;
    static final int INCREMENTS = 100000;

    static void runTest(boolean safe) throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    if (safe) {
                        counter.incrementSafe();
                    } else {
                        counter.increment();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println((safe ? "Synchronized" : "Unsynchronized")
                + " count: " + counter.count);
    }

    public static void main(String[] args)
            throws InterruptedException {
        runTest(false);
        runTest(true);
        System.out.println("Expected count: 1000000");
    }
}
