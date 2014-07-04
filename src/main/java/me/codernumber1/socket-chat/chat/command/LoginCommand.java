package chat.command;

import chat.ConnectionThread;
import chat.History;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class LoginCommand implements ICommand {
    public static final int ARGS_NUM = 1;
    public static final String NAME = "/login";

    private String name;

    public LoginCommand(String name)
    {
        this.name = name;
    }

    public String getName() 
    {
        return NAME;
    }

    private boolean isValidName(String name)
    {
        return ! StringUtils.containsIgnoreCase(name, "<private>");
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
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

    public String toString()
    {
        return "LoginCommand \"" + name + "\"";
    }
}