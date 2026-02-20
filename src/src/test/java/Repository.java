import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Repository {
    private final Queue<Job> queue = new LinkedList<>();
    private final int capacity;

    private final Semaphore empty;
    private final Semaphore full;

    private final Lock lock = new ReentrantLock();

    public Repository(int capacity) {
        this.capacity = capacity;
        this.empty = new Semaphore(capacity);
        this.full = new Semaphore(0);
    }

    public void produce (Job job) throws InterruptedException {
        empty.acquire();
        lock.lock();
        try {
            queue.add(job);
        } finally {
            lock.unlock();
        }
        full.release();
    }

    public Job consumer() throws InterruptedException {
        full.acquire();
        Job job;
        lock.lock();
        try  {
            job = queue.remove();
        } finally {
            lock.unlock();
        }
        empty.release();
        return job;
    }
}
