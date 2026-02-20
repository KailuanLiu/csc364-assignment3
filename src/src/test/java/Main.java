public class Main {
    public static void main(String[] args) {
        Repository queue = new Repository(10);
        int numConsumers = 3;
        int numProducers = 1;
        for(int i = 0; i < numProducers; i++) {
            new Thread(new Producer(queue)).start();
        }
        for(int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(queue)).start();
        }


    }
}