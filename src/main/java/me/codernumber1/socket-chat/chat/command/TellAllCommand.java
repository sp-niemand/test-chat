/**
 * TellAllCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import java.util.Set;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.MessageFormatter;
import me.codernumber1.socket_chat.chat.exception.HistoryException;
import me.codernumber1.socket_chat.chat.Logger;

/**
 * Outputs text message to every user in chat
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
class TellAllCommand implements ICommand {
    public static final String NAME = "/tellAll";

    private String message;

    /**
     * Constructor
     * 
     * @param message Message to output
     */
    public TellAllCommand(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    ) {
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

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }

    /**
     * Returns string representation of the command. Used to log commands
     * 
     * @return
     */
    public String toString() {
        return "TellAllCommand \"" + message + "\"";
    }
}