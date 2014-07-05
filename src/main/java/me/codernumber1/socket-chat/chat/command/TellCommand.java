package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.MessageFormatter;

class TellCommand implements ICommand {
    public static final String NAME = "/tell";

    private String name;
    private String message;

    public TellCommand(String name, String message)
    {
        this.name = name;
        this.message = message;
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        if (this.name == clientName) {

            return;
        }
        if (! connectionMap.containsKey(this.name)) {
            ConnectionThread sourceConnection = connectionMap.get(clientName);
            sourceConnection.print("No such user in me.codernumber1.socket_chat.chat!");
            return;
        }
        ConnectionThread targetConnection = connectionMap.get(this.name);
        targetConnection.print(MessageFormatter.getPrivateMessageText(clientName, message));
    }

    public String getName()
    {
        return NAME;
    }

    public String toString()
    {
        return "TellCommand " + "\"" + name + "\" \"" + message + "\"";
    }
}