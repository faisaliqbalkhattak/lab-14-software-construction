import java.util.concurrent.CopyOnWriteArrayList;

public class Task3_ConcurrentDataStructures {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();

        Thread writer1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedList.add(i);
                System.out.println("Writer 1 added: " + i);
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                sharedList.add(i);
                System.out.println("Writer 2 added: " + i);
            }
        });

        Thread reader = new Thread(() -> {
            try {
                Thread.sleep(1);
                for (Integer num : sharedList) {
                    System.out.println("Reader reading: " + num);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        writer1.start();
        writer2.start();
        reader.start();

        writer1.join();
        writer2.join();
        reader.join();

        System.out.println("Final List: " + sharedList);
    }
}
