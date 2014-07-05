/**
 * QuitCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import java.util.Map;

/**
 * Quits from the chat
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class QuitCommand implements ICommand {
    public static final int ARGS_NUM = 0;
    public static final String NAME = "/quit";

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    ) {
        connectionMap.remove(clientName);
    }

    /**
     * Returns string representation of the command. Used to log commands
     * 
     * @return
     */
    public String toString() {
        return "QuitCommand";
    }
}