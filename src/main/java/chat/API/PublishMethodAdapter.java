package chat.API;

import chat.server.ChatServer;
import chat.server.ChatSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/7/17.
 */
public class PublishMethodAdapter implements Adapter {

    Map<String, PublishMethod> methods;
    private Map<String, ChatServer> servers;

    /** Constuctor. */
    public PublishMethodAdapter() {
        methods = new HashMap<>();
        servers = new HashMap<>();
    }

    @Override
    public void adapt(String serverName, String name, String message) {
        PublishMethod temp;
        if (message.contains("@")) {
            temp = methods.get("SendIndividualMSG");
            temp.setSubscribers(servers.get(serverName).getSubscribers());
            temp.publish(name, message);
        } else {
            temp = methods.get("BroadCast");
            temp.setSubscribers(servers.get(serverName).getSubscribers());
            temp.publish(name, message);
        }
    }

    @Override
    public void addMethod(PublishMethod method) {
        methods.put(method.getName(), method);
    }

    @Override
    public void addServers(ChatServer server) {servers.put(server.getName(), server); }
}
