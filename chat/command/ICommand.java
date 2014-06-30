package chat.command;

import java.util.*;
import chat.ConnectionThread;
import chat.History;

public interface ICommand {
    public void execute(
        Map<String, ConnectionThread> connectionMap,
        History history
    );
}