import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Repository<T> {
    private final Queue<T> queue;

    private final Semaphore empty;
    private final Semaphore full;

    private final Lock lock = new ReentrantLock();

    public Repository(int capacity) {
        this.queue = new LinkedList<>();
        this.empty = new Semaphore(capacity);
        this.full = new Semaphore(0);
    }

    public void produce (T item) throws InterruptedException {
        empty.acquire();
        lock.lock();
        try {
            queue.add(item);
        } finally {
            lock.unlock();
        }
        full.release();
    }

    public T consume() throws InterruptedException {
        full.acquire();
        T item;
        lock.lock();
        try  {
            item = queue.remove();
        } finally {
            lock.unlock();
        }
        empty.release();
        return item;
    }
}
