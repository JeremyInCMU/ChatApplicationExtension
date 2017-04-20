package chat.User;
import chat.API.*;
import chat.gui.ChatPanel;
import chat.gui.SimpleChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Simple chat application.
 */
public class Main {
    public static void main(String[] args) {
        ChatAppAPI api = new ChatAppAPI();
        Adapter adapter = api.getAdapter();
        SimpleChatClient app = api.initAppGUI("My App", adapter);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.startChatSession(app.getNames(), api.getServer("main"));
                List<String> names = api.getAllName(app);
                for (String name : names) {
                    ChatPanel panel = api.getChatPanel(app, name);
                    ExtensionPanel extensionPanel = new ExtensionPanel(api, app, adapter);
                    panel.setExtensionArea(extensionPanel);
                }
                PublishMethod temp = new SendIndividualMSG("SendIndividualMSG");
                api.addMethod(temp, adapter);
            }
        };
        api.setStartActionListener(app, listener);
    }
}