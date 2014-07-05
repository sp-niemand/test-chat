package me.codernumber1.socket_chat.chat.command;

import java.util.Map;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.exception.HistoryException;
import me.codernumber1.socket_chat.chat.Logger;

public class GetHistoryCommand implements ICommand {
    public static final String NAME = "/getHistory";
    public static final int ARGS_NUM = 1;
    public static final int DEFAULT_COUNT = 30;

    private int count = DEFAULT_COUNT;

    public GetHistoryCommand()
    {
    }

    public GetHistoryCommand(int count)
    {
        this.count = count;
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        ConnectionThread clientConnectionThread = connectionMap.get(clientName);
        if (clientConnectionThread == null) {
            return;
        }

        String[] linesToPrint;
        try {
            linesToPrint = history.getLastMessages(count);    
        } catch (HistoryException e) {
            Logger.instance().log("GetHistoryCommand failed because of exception: " + e);
            return;
        }
        
        for (String line : linesToPrint) {
            clientConnectionThread.print(line);
        }
    }

    public String getName()
    {
        return NAME;
    }
}