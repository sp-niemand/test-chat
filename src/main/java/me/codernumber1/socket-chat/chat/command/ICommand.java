package chat.command;

import java.util.*;
import chat.ConnectionThread;
import chat.History;

public interface ICommand {
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    );

    public String getName();
}