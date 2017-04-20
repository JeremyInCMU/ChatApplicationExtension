package chat.server;

/**
 * Callback for ChatServer, called each time a message arrives.
 */
public interface ChatSubscriber {
    String name = null;

    /**
     * Handles a message received from chat server, which includes sender's name
     * and the message text.
     * 
     * @param name
     *            sender's name
     * @param message
     *            message text
     */
    void handleMessage(String name, String message);

    /**
     * Get name of chat subscriber.
     * @return the name of this chat subscriber.
     */
    String getName();

    /**
     * Getter for server of this subscriber.
     * @return server of the subscriber
     */
    ChatServer getServer();
}
