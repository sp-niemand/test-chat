package chat;

import java.net.*;
import java.io.*;
import java.util.*;

import chat.command.ICommand;

public class Server {
    static final int DEFAULT_PORT = 23;

    private int port;
    private int guestId = 1;
    private Map<String, ConnectionThread> connectionMap
        = Collections.synchronizedMap(new HashMap<String, ConnectionThread>());
    private History history;
    private Logger logger;
    private ServerSocket serverSocket;

    public Server() {
        this.port = Server.DEFAULT_PORT;
    }

    public Server(int port) {
        this.port = port;
    }

    public Server(int port, Logger logger) {
        this(port);
        this.logger = logger;
    }

    private String getNewGuestName() 
    {
        return "guest" + this.guestId ++;
    }

    void commandExecute(String clientName, ICommand command)
    {
        command.execute(clientName, connectionMap, history);
        String logMsg = "clientName=" + clientName + "; connectionMap=" + connectionMap;
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
        
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();  
                String clientName = this.getNewGuestName();
                ConnectionThread newConnection = new ConnectionThread(
                    clientName, clientSocket, this, logger
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