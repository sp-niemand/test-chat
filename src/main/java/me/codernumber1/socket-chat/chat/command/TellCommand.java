package chat.command;

import java.util.*;
import chat.ConnectionThread;
import chat.History;

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
            sourceConnection.print("No such user in chat!");
            return;
        }
        ConnectionThread targetConnection = connectionMap.get(this.name);
        targetConnection.print(clientName + " :<private>: " + message);
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