package chat.API;
import chat.server.ChatServer;
import chat.server.ChatSubscriber;

import java.util.List;

/**
 * This interface is designed to represent publish
 * Created by Jeremy on 4/7/17.
 */
public interface Adapter {

    /**
     * The adapt method to transit common publish method to a specific publish method.
     * @param serverName: name of a server.
     * @param name: name of message sender.
     * @param message: the message sent out.
     */
    public void adapt(String serverName, String name, String message);

    /**
     * Add method to the adapter.
     * @param method: a publish method added to the adpater.
     */
    public void addMethod(PublishMethod method);

    /**
     * Add a chat server to the adpater.
     */
    public void addServers(ChatServer server);
}
