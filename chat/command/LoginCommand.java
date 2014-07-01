package chat.command;

import chat.ConnectionThread;
import chat.History;
import java.util.*;

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

    public int getArgsNumber()
    {
        return ARGS_NUM;
    }

    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    )
    {
        System.out.println("LoginCommand.execute()");
        System.out.println(connectionMap);
        System.out.println(history);
    }
}