import java.util.concurrent.*;

class Task implements Runnable{
    private int taskId;

    Task(int taskId){
        this.taskId = taskId;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + " completed Task => " + taskId);

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " was interrupted!!!");
        }
    }
}

public class ThreadPoolRunner{
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for(int i=1; i<=10; i++){
            pool.submit(new Task(i));
        }

        pool.shutdown();

        // pool.awaitTermination(10, TimeUnit.SECONDS);
        // System.out.println("All tasks are completed!!!");

        try{
            pool.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("All tasks are completed!!!");
        }catch(InterruptedException ie){
            System.err.println();
        }
    }
}