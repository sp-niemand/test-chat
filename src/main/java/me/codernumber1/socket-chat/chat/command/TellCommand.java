/**
 * TellCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;
import me.codernumber1.socket_chat.chat.MessageFormatter;

/**
 * This command outputs text message to a single user in the chat
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
class TellCommand implements ICommand {
    public static final String NAME = "/tell";

    private String name;
    private String message;

    /**
     * Constructor
     * 
     * @param name Message recipient's name
     * @param message Message to show
     */
    public TellCommand(String name, String message) {
        this.name = name;
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
        if (this.name == clientName) {

            return;
        }
        if (! connectionMap.containsKey(this.name)) {
            ConnectionThread sourceConnection = connectionMap.get(clientName);
            sourceConnection.print("No such user in me.codernumber1.socket_chat.chat!");
            return;
        }
        ConnectionThread targetConnection = connectionMap.get(this.name);
        targetConnection.print(MessageFormatter.getPrivateMessageText(clientName, message));
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
        return "TellCommand " + "\"" + name + "\" \"" + message + "\"";
    }
}