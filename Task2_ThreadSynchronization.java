public class Task2_ThreadSynchronization {
    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) counter.increment();
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final Counter Value: " + counter.getCount());
    }
}
