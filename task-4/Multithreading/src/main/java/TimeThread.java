import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
class TimeThread extends Thread {
    private final int n;

    public TimeThread(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше нуля");
        }

        this.n = n;
        setDaemon(true);
    }

    @Override
    public void run() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (!isInterrupted()) {
            try {
                Thread.sleep(n * 1000L);
            } catch (InterruptedException e) {
                return;
            }

            System.out.println(LocalTime.now().format(format));
        }
    }
}