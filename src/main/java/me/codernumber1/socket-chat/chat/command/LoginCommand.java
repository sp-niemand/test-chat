/**
 * LoginCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * This command is used to change user's name in chat
 * 
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class LoginCommand implements ICommand {
    public static final int ARGS_NUM = 1;
    public static final String NAME = "/login";

    private String name;

    /**
     * Constructor
     * 
     * @param name New user's name
     */
    public LoginCommand(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }

    /**
     * Checks if given name is valid to be set
     * 
     * @param Name to check
     * 
     * @return Is name valid?
     */
    private boolean isValidName(String name) {
        return ! StringUtils.containsIgnoreCase(name, "<private>");
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

        if (! isValidName(this.name)) {
            clientConnectionThread.print("Invalid name! Try another one!");
            return;
        }

        if (connectionMap.containsKey(this.name)) {
            clientConnectionThread.print("Name already in use!");
            return;
        }

        connectionMap.remove(clientName);
        clientConnectionThread.setClientName(this.name);
        connectionMap.put(this.name, clientConnectionThread);
    }

    /**
     * Returns string representation of the command. Used to log commands
     * 
     * @return
     */
    public String toString() {
        return "LoginCommand \"" + name + "\"";
    }
}