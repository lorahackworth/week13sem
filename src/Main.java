import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Task a = new Task();

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);
        Thread t3 = new Thread(a);

        //isn't needed, just to set names for threads
        // for ex. if a specific thread is only used for task, you could name it "taskthread"
        t1.setName("Thread1");
        t2.setName("Thread2");
        t3.setName("Thread3");

        t1.start();
        t2.start();
        t3.start();

    }
}

class Task implements Runnable{
    //however many permits you have, the however many threads you can run at a time
    //if able, we don't want any idle threads
    Semaphore sem = new Semaphore(3, true);

    @Override
    public void run() {
        try{
            sem.acquire();
            System.out.println("Run by: " + Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                System.out.println("Running thread: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            sem.release();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}