class Main{
    private static Object obj = new Object();
    private static int turn = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(()->{
                    for (int i = 0; i < 5; i++) {
                        synchronized (obj) {
                            while(turn != 0){
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println(Thread.currentThread().getName());
                            turn = 1;
                            obj.notifyAll();
                        }
                    }
        });
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                synchronized (obj) {
                    while(turn != 1){
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    turn = 0;
                    obj.notifyAll();
                }
            }
        });
        thread0.start();
        thread1.start();
    }
}