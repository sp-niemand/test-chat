package chat.command;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

public class Protocol {
    private static final char COMMAND_DIVIDER = ' ';

    public static ICommand parseData(String data)
    {
        String[] parts = data.split(Character.toString(COMMAND_DIVIDER));
        String commandName = parts[0];
        String message;
        switch (commandName) {
            case LoginCommand.NAME:
                return parts.length < LoginCommand.ARGS_NUM + 1
                    ? null 
                    : new LoginCommand(parts[1]);

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