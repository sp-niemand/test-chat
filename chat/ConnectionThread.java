package chat;

import java.net.*;
import java.io.*;
import chat.command.Protocol;
import chat.exception.SocketException;
import chat.Logger;

public class ConnectionThread extends Thread {
    private Socket socket;
    private Server server;
    private Logger logger;
    private BufferedReader in;
    private PrintWriter out;

    public ConnectionThread(Socket socket, Server server, Logger logger) {
        this.socket = socket;
        this.server = server;
        this.logger = logger;

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);            
            out.println("Welcome!");
        } catch (IOException e) {
            logger.log("ConnectionThread.constructor(): " + e);
        }
    }

    public void run()
    {
        try {
            logger.log("Connection thread created");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                logger.log(inputLine);
                server.commandExecute(Protocol.parseData(inputLine));
            }
        } catch (IOException e) {
            logger.log("ConnectionThread.run(): " + e);
        }
    }

    public void print(String msg)
    {
        out.println(msg);
    }
}