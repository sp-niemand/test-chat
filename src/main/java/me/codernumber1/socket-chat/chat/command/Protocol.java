/**
 * LoginCommand class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.command;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * This class instantiates commands from user's input
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class Protocol {
    private static final char COMMAND_DIVIDER = ' ';

    /**
     * Instantiates commands from user's input
     * 
     * @param data User's input
     * 
     * @return The command instance. NULL if command can not be instantiated.
     */
    public static ICommand parseData(String data) {
        String[] parts = data.split(Character.toString(COMMAND_DIVIDER));
        String commandName = parts[0];
        String message;
        switch (commandName) {
        case LoginCommand.NAME:
            return parts.length < LoginCommand.ARGS_NUM + 1
                ? null 
                : new LoginCommand(parts[1]);

        case GetHistoryCommand.NAME:
            if (parts.length < GetHistoryCommand.ARGS_NUM + 1) {
                return new GetHistoryCommand();
            } else {
                int count;
                try {
                    count = new Integer(parts[1]);    
                } catch (NumberFormatException e) {
                    return new GetHistoryCommand();
                }
                return new GetHistoryCommand(count);
            }

        case QuitCommand.NAME:
            return new QuitCommand();

        case UserListCommand.NAME:
            return new UserListCommand();

        case TellCommand.NAME:
            if (parts.length <= 2) {
                return null;
            }
            String[] messageParts 
                = Arrays.copyOfRange(parts, 2, parts.length);
            String tellCommandMessage 
                = StringUtils.join(messageParts, COMMAND_DIVIDER);
            return new TellCommand(parts[1], tellCommandMessage);

        case TellAllCommand.NAME:
            String tellAllCommandMessage 
                = data.substring(TellAllCommand.NAME.length());
            tellAllCommandMessage 
                = tellAllCommandMessage.replaceAll("^\\s+","");
            return tellAllCommandMessage.length() == 0
                ? null 
                : new TellAllCommand(tellAllCommandMessage);

        default:
            return new TellAllCommand(data);
        }
    }
}