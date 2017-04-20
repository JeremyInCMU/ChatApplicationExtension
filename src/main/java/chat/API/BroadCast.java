package chat.API;

import chat.API.PublishMethod;
import chat.server.ChatSubscriber;

import java.util.List;

/**
 * Created by Jeremy on 4/7/17.
 */
public class BroadCast implements PublishMethod {
    /** contains subscribers*/
    private String name;
    private List<ChatSubscriber> subscribers;

    /** Constructor */
    public BroadCast(String newName) {
        name = newName;
    }


    @Override
    public void publish(String name, String message) {
        for (ChatSubscriber subscriber : subscribers) {
            subscriber.handleMessage(name, message);
        }
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setSubscribers(List<ChatSubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
