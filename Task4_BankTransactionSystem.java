import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Task4_BankTransactionSystem {
    static class BankAccount {
        private AtomicInteger balance = new AtomicInteger(1000);

        public void deposit(int amount) {
            balance.addAndGet(amount);
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", Balance: " + balance.get());
        }

        public void withdraw(int amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", Balance: " + balance.get());
        }

        public int getBalance() {
            return balance.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Random rand = new Random();

        Thread[] clients = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int clientId = i + 1;
            clients[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    if (rand.nextBoolean()) {
                        account.deposit(rand.nextInt(100) + 50);
                    } else {
                        account.withdraw(rand.nextInt(50) + 10);
                    }
                }
            }, "Client-" + clientId);
        }

        for (Thread client : clients) client.start();
        for (Thread client : clients) client.join();

        System.out.println("Final Account Balance: " + account.getBalance());
    }
}
