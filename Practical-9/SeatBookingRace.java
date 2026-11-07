import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class Booking {
    private int seatsLeft = 5;
    private int successful = 0;

    synchronized boolean book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successful++;
            return true;
        }
        return false;
    }

    int getSeatsLeft() {
        return seatsLeft;
    }

    int getSuccessful() {
        return successful;
    }
}

public class SeatBookingRace {
    public static void main(String[] args)
            throws InterruptedException {
        Booking booking = new Booking();
        AtomicInteger successes = new AtomicInteger(0);
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    start.await();

                    if (booking.book()) {
                        successes.incrementAndGet();
                        System.out.println("Booking successful");
                    } else {
                        System.out.println("No seats available");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        start.countDown();

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Successful bookings: "
                + successes.get());
        System.out.println("Seats left: "
                + booking.getSeatsLeft());
    }
}
    
