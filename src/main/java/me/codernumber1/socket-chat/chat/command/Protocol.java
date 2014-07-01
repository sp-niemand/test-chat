package chat.command;

public class Protocol {
    private static final String COMMAND_DIVIDER = " ";

    public static ICommand parseData(String data)
    {
        String[] parts = data.split(COMMAND_DIVIDER);
        String commandName = parts[0];
        switch (commandName) {
            case LoginCommand.NAME:
                return parts.length < LoginCommand.ARGS_NUM + 1
                    ? null 
                    : new LoginCommand(parts[1]);
            case QuitCommand.NAME: 
                return new QuitCommand();
            default:
                return null;
        }
    }
}