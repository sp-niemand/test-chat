package me.codernumber1.socket_chat.chat.command;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import java.util.Map;

public class QuitCommand implements ICommand {
    public static final int ARGS_NUM = 0;
    public static final String NAME = "/quit";

    public String getName() 
    {
        return NAME;
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        connectionMap.remove(clientName);
    }

    public String toString()
    {
        return "QuitCommand";
    }
}