/**
 * UserListCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;

/**
 * Outputs the list of users in chat for the command publisher
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class UserListCommand implements ICommand {
    public static final String NAME = "/userList";

    /**
     * {@inheritDoc}
     */
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    ) {
        StringBuffer messageBuffer = new StringBuffer("Users in chat:\n");
        for (String userName : connectionMap.keySet()) {
            messageBuffer.append(userName);
            messageBuffer.append("\n");
        }
        ConnectionThread targetConnection = connectionMap.get(clientName);
        targetConnection.print(messageBuffer.toString());
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }
}