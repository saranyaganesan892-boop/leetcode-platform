import java.util.concurrent.Semaphore;

class Foo {

    private Semaphore second = new Semaphore(0);
    private Semaphore third = new Semaphore(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        second.release();   // Allow second() to execute
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();   // Wait until first() completes
        printSecond.run();
        third.release();    // Allow third() to execute
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();    // Wait until second() completes
        printThird.run();
    }
}
