package chat.API;

import chat.server.ChatSubscriber;

import java.util.List;

/**
 * Created by Jeremy on 4/7/17.
 */
public interface PublishMethod {
    /** Name of the publish method */
    String name = null;
    List<ChatSubscriber> subscribers = null;

    /**
     * This method is designed to implement message sending.
     * @param name
     * @param message
     */
    public void publish(String name, String message);


    /**
     * Get name of this publish method.
     * @return name of this publish method.
     */
    public String getName();

    /**
     * Set subscribers.
     */
    public void setSubscribers(List<ChatSubscriber> subscribers);
}
