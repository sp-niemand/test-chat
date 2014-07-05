package me.codernumber1.socket_chat.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import me.codernumber1.socket_chat.chat.command.ICommand;
import me.codernumber1.socket_chat.chat.exception.HistoryException;
import me.codernumber1.socket_chat.chat.exception.ServerException;

public class Server {
    static final int DEFAULT_PORT = 23;

    private int port = DEFAULT_PORT;
    private int guestId = 1;
    private Map<String, ConnectionThread> connectionMap
        = Collections.synchronizedMap(new HashMap<String, ConnectionThread>());
    private History history;
    private Logger logger = Logger.instance();
    private ServerSocket serverSocket;

    public Server(int port) throws ServerException
    {
        this.port = port;

        try {
            this.history = new History();
            this.history.ensureTable();     
        } catch (HistoryException e) {
            throw new ServerException("History initialization failed", e);
        }
    }

    private String getNewGuestName() 
    {
        return "guest" + this.guestId ++;
    }

    void commandExecute(String clientName, ICommand command)
    {
        command.execute(clientName, connectionMap, history);
        String logMsg = "Server.commandExecute() environment:\n"
            + "    clientName   = " + clientName + ";\n" 
            + "    connectionMap= " + connectionMap + ";\n"
            + "    command      = " + command;
        logger.log(logMsg);
    }

    public void run() throws IOException
    {
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            logger.log("Exception caught when trying to listen on port "
                + this.port + " or listening for a connection");
            logger.log(e);
            return;
        }

        logger.log("Server started");
        
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();  
                String clientName = this.getNewGuestName();
                ConnectionThread newConnection = new ConnectionThread(
                    clientName, clientSocket, this
                );
                connectionMap.put(clientName, newConnection);
                newConnection.start();
            } catch (IOException e) {
                logger.log("Exception caught when trying to listen on port "
                    + this.port + " or listening for a connection");
                logger.log(e);
                break;
            }

            logger.log(connectionMap.toString());
        }
    }
}