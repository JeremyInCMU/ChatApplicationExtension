package chat.client;

import java.util.ArrayList;
import java.util.List;

import chat.server.ChatServer;
import chat.server.ChatSubscriber;

/**
 *	A simple implementation of a ChatClient framework
 */
public class ChatClient implements ChatSubscriber{
    /** Chat participant's name. */
    private final String name;
    
    /** Chat server that manages chat messages. */
    private final ChatServer server;
    
	private final List<ChatClientListener> listeners = new ArrayList<>();
	
	public ChatClient(ChatServer server, String name) {
        if (name == null || server == null) {
            throw new NullPointerException("Server and name must not be null.");
        }
        
        this.name = name;
        this.server = server;
        
        // Let server know that this participant is subscribing to chat
        // messages.
        server.subscribe(this);
	}

	public ChatClient(ChatSubscriber subscriber) {
		this.name = subscriber.getName();
		this.server = subscriber.getServer();
	}
	
	/**
	 * Getter for the name of the client
	 * @return client name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the server of the client.
	 * @return the server.
	 */
	@Override
	public ChatServer getServer() { return server; }

	/**
	 * Send the message to the server
	 * @param message : content of the message
	 * @param name : sender's name
	 */
	public void send(String message, String name) {
		server.publish(name, message);
	}
	
    /**
     * Notify the listeners of incoming messages
     */
    @Override
    public void handleMessage(String name, String message) {
        for (ChatClientListener listener : listeners) {
        	listener.onIncomingMsg(name, message);
        }
    }
	
    /**
     * Add Chat Client Listeners
     * @param listener : the listener to be added
     */
	public void addListener(ChatClientListener listener) {
		listeners.add(listener);
	}
}
