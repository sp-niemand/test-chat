/**
 * ICommand interface
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;

/**
 * An interface which every chat command must implement
 * 
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public interface ICommand {

    /**
     * Executes the command
     * 
     * @param clientName Command publisher
     * @param connectionMap Connections storage
     * @param history History instance
     */
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    );

    /**
     * Returns the name for this command
     * 
     * @return Command name
     */
    public String getName();
}