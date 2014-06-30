package chat.command;

import chat.ConnectionThread;
import chat.History;
import java.util.*;

public class LoginCommand implements ICommand {
    private String name;

    public LoginCommand(String name)
    {
        this.name = name;
    }

    public void execute(
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        System.out.println("LoginCommand.execute()");
        System.out.println(connectionMap);
        System.out.println(history);
    }
}