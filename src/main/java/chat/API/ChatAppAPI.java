package chat.API;

import chat.gui.ChatPanel;
import chat.gui.SimpleChatClient;
import chat.server.ChatServer;
import chat.server.ChatSubscriber;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/7/17.
 */
public class ChatAppAPI<E> implements ExtensionAPI{

    private Map<String, ChatServer> servers = new HashMap<>();

    @Override
    public Adapter getAdapter() { return new PublishMethodAdapter(); }


    @Override
    public SimpleChatClient initAppGUI(String name, Adapter adapter) {

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ChatServer server = new ChatServer("main", adapter);
        servers.put("main", server);
        SimpleChatClient chatApp = new SimpleChatClient(frame, server);

        frame.add(chatApp);

        //Visualize
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        this.addMethod(new BroadCast("BroadCast"), adapter);

        return chatApp;
    }

    @Override
    public void addMethod(PublishMethod method, Adapter adapter) {
        adapter.addMethod(method);
    }


    @Override
    public ChatServer getServer(String name) {
        return servers.get(name);
    }

    @Override
    public void addNewServer(SimpleChatClient app, Adapter adapter, String name, List memberName) {
        ChatServer server = new ChatServer(name, adapter);
        app.startChatSession(memberName, server);
    }

    @Override
    public ChatPanel getChatPanel(SimpleChatClient app, String name) {
        return app.getChatPanel(name);
    }

    @Override
    public void setStartActionListener(SimpleChatClient app, ActionListener listener) {
        app.setActionListner(listener);
    }

//    @Override
//    public boolean isAllSet(SimpleChatClient app) {
//        System.out.println(app.allSet());
//        return app.allSet();
//    }

    @Override
    public List<String> getAllName(SimpleChatClient app) { return app.getNames(); }
}
