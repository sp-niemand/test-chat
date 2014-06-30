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

    public void commandExecute(ICommand command)
    {
        command.execute(connectionMap, history);
    }

    public void run()
    {
        while (true) {
            try ( 
                ServerSocket serverSocket = new ServerSocket(this.port);
                Socket clientSocket = serverSocket.accept();    
            )   {
                ConnectionThread newConnection = new ConnectionThread(clientSocket, this, logger);
                connectionMap.put(this.getNewGuestName(), newConnection);
                newConnection.start();
            } catch (IOException e) {
                logger.log("Exception caught when trying to listen on port "
                    + this.port + " or listening for a connection");
                logger.log(e);
                break;
            }

            logger.log(connectionMap.toString());
        }
            // String inputLine, outputLine;
             
            // Initiate conversation with client
            // KnockKnockProtocol kkp = new KnockKnockProtocol();
            // outputLine = kkp.processInput(null);
            // out.println(outputLine);
 
            // while ((inputLine = in.readLine()) != null) {
            //     outputLine = kkp.processInput(inputLine);
            //     out.println(outputLine);
            //     if (outputLine.equals("Bye."))
            //         break;
            // }

    }
}