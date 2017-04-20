package chat.User;

import chat.API.PublishMethod;
import chat.server.ChatSubscriber;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 4/7/17.
 */
public class SendIndividualMSG implements PublishMethod {
    private String name;
    private List<ChatSubscriber> subscribers;

    /** Constructor */
    public SendIndividualMSG(String newName) {
        name = newName;
    }

    @Override
    public void publish(String name, String message) {
        String[] msgs = message.split(" ");
        StringBuilder updateMSG = new StringBuilder();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < msgs.length; i++) {
            if (msgs[i].contains("@")) { names.add(msgs[i].replaceAll("@", "")); }
            else { updateMSG.append(msgs[i] + " "); }
        }

        for (String n : names) {
            for (ChatSubscriber subscriber : subscribers) {
                if (subscriber.getName().equals(n)) {
                    subscriber.handleMessage(name, updateMSG.toString());
                }
            }
        }
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setSubscribers(List<ChatSubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
