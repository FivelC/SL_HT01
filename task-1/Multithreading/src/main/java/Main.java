class Main{
    private static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Второй: " + Thread.currentThread().getState());
            synchronized (obj){
                try {
                    obj.wait();
                    Thread.sleep(15_000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Первый: " + thread.getState());
        synchronized (obj){
            thread.start();
            while (thread.getState() != Thread.State.BLOCKED) {
                Thread.sleep(1);
            }
            System.out.println("Третий: " + thread.getState());
        }
        while (thread.getState() != Thread.State.WAITING) {
            Thread.sleep(1);
        }
        System.out.println("Четвертый: " + thread.getState());
        synchronized (obj) {
            obj.notifyAll();
        }
        while (thread.getState() != Thread.State.TIMED_WAITING) {
            Thread.sleep(1);
        }
        System.out.println("Пятый: " + thread.getState());
        thread.interrupt();
        thread.join();
        System.out.println("Шестой: " + thread.getState());
    }
}