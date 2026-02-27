public class Consumer implements Runnable{
    public final Repository<Job> queue;

    public Consumer (Repository<Job> queue) {
        this.queue = queue;
    }

    @Override
    public void run () {
        try {
            while(true) {
                Job job = queue.consume();
                System.out.println("Locally processed " + job.process());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
