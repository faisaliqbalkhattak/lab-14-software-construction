public class Task1_Multithreading {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Thread 1 - Number: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Thread 2 - Square: " + (i * i));
            }
        });

        thread1.start();
        thread2.start();
    }
}
