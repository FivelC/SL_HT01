class Main {
    public static void main(String[] args) throws InterruptedException {
        TimeThread thread = new TimeThread(3);
        thread.start();

        Thread.sleep(10_000);
    }
}