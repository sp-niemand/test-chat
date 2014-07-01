package chat;

import java.net.*;
import java.io.*;
import chat.command.*;
import chat.exception.*;

public class ConnectionThread extends Thread {
    private Socket socket;
    private Server server;
    private Logger logger;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    public ConnectionThread(String clientName, Socket socket, Server server, Logger logger) {
        this.clientName = clientName;
        this.socket = socket;
        this.server = server;
        this.logger = logger;

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);            
        } catch (IOException e) {
            logger.log("ConnectionThread.constructor(): " + e);
        }
    }

    public void run()
    {
        try {
            logger.log("Connection thread created");
            out.println("Welcome!");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                logger.log(inputLine);
                ICommand command = Protocol.parseData(inputLine);
                if (command instanceof ICommand) {
                    server.commandExecute(clientName, command);    
                }
                if (command instanceof QuitCommand) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.log("ConnectionThread.run(): " + e);
        } finally {
            try {
                out.close();
                in.close();
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    public void print(String msg)
    {
        out.println(msg);
    }
}