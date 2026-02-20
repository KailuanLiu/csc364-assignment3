public class Consumer implements Runnable{
    public final Repository queue;

    public Consumer (Repository queue) {
        this.queue = queue;
    }

    @Override
    public void run () {
        try {
            while(true) {
                Job job = queue.consumer();
                System.out.println(job.process());
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
