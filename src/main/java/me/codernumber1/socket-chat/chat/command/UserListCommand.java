package chat.command;

import java.util.*;
import chat.ConnectionThread;
import chat.History;

public class UserListCommand implements ICommand {
    public static final String NAME = "/userList";

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        StringBuffer messageBuffer = new StringBuffer("Users in chat:\n");
        for (String userName : connectionMap.keySet()) {
            messageBuffer.append(userName);
            messageBuffer.append("\n");
        }
        ConnectionThread targetConnection = connectionMap.get(clientName);
        targetConnection.print(messageBuffer.toString());
    }

    public String getName()
    {
        return NAME;
    }
}