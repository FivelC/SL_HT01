import java.util.ArrayDeque;
import java.util.Random;

class Main {
    private static final Object obj = new Object();
    private static final ArrayDeque<Integer> buffer = new ArrayDeque<>();
    private static final int capacity = 3;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                synchronized (obj) {
                    while (buffer.size() == capacity) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    int number = random.nextInt(100);
                    buffer.addLast(number);
                    System.out.println("Производитель добавил: " + number);
                    obj.notifyAll();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (obj) {
                    while (buffer.isEmpty()) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    int number = buffer.removeFirst();
                    System.out.println("Потребитель забрал: " + number);
                    obj.notifyAll();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}