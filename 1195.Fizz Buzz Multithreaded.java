import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int curr = 1;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (curr <= n && !(curr % 3 == 0 && curr % 5 != 0)) {
                    condition.await();
                }

                if (curr > n) {
                    condition.signalAll();
                    return;
                }

                printFizz.run();
                curr++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (curr <= n && !(curr % 5 == 0 && curr % 3 != 0)) {
                    condition.await();
                }

                if (curr > n) {
                    condition.signalAll();
                    return;
                }

                printBuzz.run();
                curr++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (curr <= n && !(curr % 15 == 0)) {
                    condition.await();
                }

                if (curr > n) {
                    condition.signalAll();
                    return;
                }

                printFizzBuzz.run();
                curr++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x".
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (curr <= n && (curr % 3 == 0 || curr % 5 == 0)) {
                    condition.await();
                }

                if (curr > n) {
                    condition.signalAll();
                    return;
                }

                printNumber.accept(curr);
                curr++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
