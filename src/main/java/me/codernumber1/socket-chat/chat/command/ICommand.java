package me.codernumber1.socket_chat.chat.command;

import java.util.Map;
import me.codernumber1.socket_chat.chat.ConnectionThread;
import me.codernumber1.socket_chat.chat.History;

public interface ICommand {
    public void execute(
        String clientName,
        Map<String, ConnectionThread> connectionMap,
        History history
    );

    public String getName();
}