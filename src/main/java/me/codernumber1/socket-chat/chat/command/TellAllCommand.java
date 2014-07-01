package chat.command;

import java.util.*;
import chat.ConnectionThread;
import chat.History;

class TellAllCommand implements ICommand {
    public static final String NAME = "/tellAll";

    private String message;

    public TellAllCommand(String message)
    {
        this.message = message;
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        Set<String> mapKeys = connectionMap.keySet();
        for (String key : mapKeys) {
            ConnectionThread connection = connectionMap.get(key);
            if (connection.getClientName() != clientName) {
                connection.print(clientName + ": " + message);
            }
        }
    }

    public String getName()
    {
        return NAME;
    }
}