public class Main {
    public static void main(String[] args) {
        Repository<Job> queue = new Repository<>(10);
        int numConsumers = 1;
        int numProducers = 1;
        for(int i = 0; i < numProducers; i++) {
            new Thread(new Producer(queue)).start();
        }
        for(int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(queue)).start();
        }
        new Thread(new Outsourcer(queue)).start();




    }
}