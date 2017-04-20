package chat.server;

import chat.API.Adapter;
import chat.client.ChatClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple chat server. This class is not thread-safe. All methods must be
 * called from a single thread, or they must be externally synchronized.
 */
public class ChatServer {
    /** The list of current subscribers. */
    private final List<ChatSubscriber> subscribers;
    private final Adapter adapter;
    private final String name;

    public ChatServer(String name, Adapter newAdapter) {
        this.name = name;
        subscribers = new ArrayList<>();
        adapter = newAdapter;
        adapter.addServers(this);
    }

    /**
     * Add a subscriber to the chat server. The subscriber will receive messages
     * from and can publish messages to all subscribers
     * 
     * @param subscriber
     *            : the new subscriber
     */
    public void subscribe(ChatSubscriber subscriber) { subscribers.add(subscriber); }

    /**
     * Unsubscribe a user from the list of subscribers. The user will no longer
     * receive messages from other subscribers
     * 
     * @param subscriber
     *            : the user that wants to unsubscribe
     */
    public void unsubscribe(ChatSubscriber subscriber) { subscribers.remove(subscriber); }

    /**
     * Get servers' name
     * @return the name of this server.
     */
    public String getName() {
        return name;
    }

    /**
     * Get subscribers
     * @return all the subscribers.
     */
    public List<ChatSubscriber> getSubscribers() { return new ArrayList<ChatSubscriber>(subscribers); }

    /**
     * Get a subscriber.
     * @return a subscriber with a specific name.
     */
    public ChatSubscriber getSubscriber(String name) {
        for (ChatSubscriber subscriber: subscribers) {
            if (subscriber.getName().equals(name)) {
                return new ChatClient( subscriber);
            }
        }
        return null;
    }

    /**
     * Send messages.
     * @param name: name of the sender chat client.
     * @param message: message sent by the chat client.
     */
    public void publish(String name, String message) {
        adapter.adapt(this.name, name, message);
    }
}
