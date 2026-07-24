import java.util.concurrent.Semaphore;

class H2O {

    private Semaphore hydrogen = new Semaphore(2);
    private Semaphore oxygen = new Semaphore(0);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hydrogen.acquire();

        releaseHydrogen.run();

        oxygen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oxygen.acquire(2);

        releaseOxygen.run();

        hydrogen.release(2);
    }
}
