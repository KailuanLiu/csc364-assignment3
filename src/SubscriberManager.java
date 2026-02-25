import org.eclipse.paho.client.mqttv3.MqttException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubscriberManager implements PropertyChangeListener {
    private final String broker;
    private final String topicBase;
    private final Subscriber subscriber;
    private final WorkerTracker workerTracker;
    private final String workerFinderTopic;
    public SubscriberManager(String broker, String topicBase, WorkerTracker workerTracker) {
        this.broker = broker;
        this.topicBase = topicBase;
        workerFinderTopic = topicBase + "/request";
        subscriber = new Subscriber(broker);
        try {
            subscriber.addTopic(workerFinderTopic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        subscriber.addPropertyChangeListener(this);
        this.workerTracker = workerTracker;
    }

    public void addWorkerSubscription(String workerClientID) throws MqttException {
        String topic = String.format("%s/assign/%s",topicBase,workerClientID);
        subscriber.addTopic(topic);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getOldValue().equals(workerFinderTopic) && evt.getNewValue() instanceof String message) {
            workerTracker.addWorker(message);
        }
        else if(evt.getOldValue() instanceof String topic && topic.contains("/assign") && evt.getNewValue() instanceof String message && message.contains("=")){
            System.out.println(message);
            String workerClientId = topic.substring(topic.indexOf("/assign") + 8);
            String jobId = message.substring(message.indexOf("id ") + 3, message.indexOf(":"));
            workerTracker.jobFinished(workerClientId,jobId);
        }

    }

}
