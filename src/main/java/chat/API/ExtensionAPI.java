package chat.API;


import com.sun.xml.internal.ws.addressing.policy.AddressingPolicyMapConfigurator;
import chat.gui.ChatPanel;
import chat.gui.SimpleChatClient;
import chat.server.ChatServer;
import chat.server.ChatSubscriber;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Jeremy on 4/6/17.
 */
public interface ExtensionAPI<E> {

    /**
     * Get publish method adapter of this app.
     * @return the method adapter.
     */
    public Adapter getAdapter();

    /**
     * Initialize the application's GUI
     * @param name: name of this app.
     */
    public SimpleChatClient initAppGUI(String name, Adapter adapter);

    /**
     * Add publish method to adapter.
     * @param method: publish method.
     */
    public void addMethod(PublishMethod method, Adapter adapter);

    /**
     * Get server.
     * @param name of the server.
     * @return the chat server of this app.
     */
    public ChatServer getServer(String name);

    /**
     * Add a new server.
     * @param name, the name of the new server.
     * @param memberName, a list of names for the new server's memebers.
     */
    public void addNewServer(SimpleChatClient app, Adapter adapter, String name, List<String> memberName);

    /**
     * Get names of all the subscribers.
     * @return the name of all the subscribers.
     */
    public List<String> getAllName(SimpleChatClient app);

    /**
     * Get all chat panels.
     */
    public ChatPanel getChatPanel(SimpleChatClient app, String name);

//    /**
//     * Check if an app is all set.
//     */
//    public boolean isAllSet(SimpleChatClient app);

    /**
     * Set start action listener.
     */
    public void setStartActionListener(SimpleChatClient app, ActionListener listener);
}
