package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import java.util.Set;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.MessageFormatter;
import me.codernumber1.socket_chat.chat.exception.HistoryException;
import me.codernumber1.socket_chat.chat.Logger;

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
        String messageText = MessageFormatter.getMessageText(clientName, message);
        for (String key : mapKeys) {
            ConnectionThread connection = connectionMap.get(key);
            if (connection.getClientName() != clientName) {
                connection.print(messageText);
            }
        }
        try {
            history.addMessage(clientName, message);    
        } catch (HistoryException e) {
            Logger.instance().log(e.toString() + " (" + e.getCause() + ")");
        }
    }

    public String getName()
    {
        return NAME;
    }

    public String toString()
    {
        return "TellAllCommand \"" + message + "\"";
    }
}