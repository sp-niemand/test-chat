/**
 * GetHistoryCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Map;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.exception.HistoryException;
import me.codernumber1.socket_chat.chat.Logger;

/**
 * Used to show chat history to the user
 * 
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class GetHistoryCommand implements ICommand {
    public static final String NAME = "/getHistory";
    public static final int ARGS_NUM = 1;
    public static final int DEFAULT_COUNT = 30;

    private int count = DEFAULT_COUNT;

    /**
     * Default constructor
     */
    public GetHistoryCommand() {
    }

    /**
     * Parameterized constructor
     * 
     * @param count Number of historical messages needed
     */
    public GetHistoryCommand(int count) {
        this.count = count;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    ) {
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
        return "GetHistoryCommand \"" + count + "\"";
    }
}