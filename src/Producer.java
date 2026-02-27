public class Producer implements Runnable{
    private final Repository<Job> queue;

    public Producer(Repository<Job> queue) {
        this.queue = queue;
    }

    @Override
    public void run(){
        while(true) {
            int first = (int)(Math.random() * 10 + 1);
            int second = (int)(Math.random() * 10 + 1);
            int operation = (int)(Math.random() * 4 + 1);
            try {
                queue.produce(new Job(first, second, operation));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
