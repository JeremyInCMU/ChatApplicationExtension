package chat.User;

import chat.API.Adapter;
import chat.API.ChatAppAPI;
import chat.gui.SimpleChatClient;
import chat.server.ChatSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;

/**
 * This panel is designed to contain the extension components.
 * Created by Jeremy on 4/8/17.
 */
public class ExtensionPanel extends JPanel{

    private final JButton addGroup;
    private ChatAppAPI api;
    private SimpleChatClient app;
    private Adapter adapter;
    private List<JCheckBox> boxes = new ArrayList<>();
    private  JTextField groupName = new JTextField(8);

    public ExtensionPanel(ChatAppAPI api, SimpleChatClient app, Adapter adapter) {
        this.api = api;
        this.app = app;
        this.adapter = adapter;
        addGroup = new JButton("Add A Group");
        this.add(addGroup, BorderLayout.WEST);
        ActionListener add = e -> {
            selectGroupMemeber();
        };
        addGroup.addActionListener(add);
    }

    public void selectGroupMemeber() {
        JFrame addGroupMember = new JFrame("Add A Group");
        JPanel n = new JPanel();
        JPanel c = new JPanel();
        JPanel w = new JPanel();
        addGroupMember.add(n, BorderLayout.NORTH);
        addGroupMember.add(c, BorderLayout.CENTER);
        addGroupMember.add(w, BorderLayout.SOUTH);
        // Set the options of group memebers

        for (Object name : api.getAllName(app)) {
            JCheckBox box = new JCheckBox((String) name);
            n.add(box);
            boxes.add(box);
        }

        JLabel groupNameLabel = new JLabel("Group Name: ");
        c.add(groupNameLabel);
        c.add(groupName);

        JButton submit = new JButton("Submit");
        w.add(submit);
        ActionListener createGroup = e -> {
            createNewGroup();
            addGroupMember.dispose();
        };
        submit.addActionListener(createGroup);

        addGroupMember.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addGroupMember.pack();
        addGroupMember.setResizable(true);
        addGroupMember.setVisible(true);
    }

    public void createNewGroup() {
        // Start session.
        List<String> newNames = new ArrayList<>();
        for(JCheckBox box : boxes) {
            if (box.isSelected()) {
                newNames.add(box.getText());
            }
        }
        api.addNewServer(app, adapter, groupName.getText(), newNames);
    }
}
