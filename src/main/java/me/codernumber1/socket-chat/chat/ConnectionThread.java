/**
 * ConnectionThread class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.codernumber1.socket_chat.chat.command.Protocol;
import me.codernumber1.socket_chat.chat.command.ICommand;
import me.codernumber1.socket_chat.chat.command.QuitCommand;
import me.codernumber1.socket_chat.chat.command.GetHistoryCommand;

/**
 * This class represents a User in the chat server
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class ConnectionThread extends Thread {
    private Socket socket;
    private Server server;
    private Logger logger = Logger.instance();
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    /**
     * Constructor
     * 
     * @param clientName User's name
     * @param socket User's connection client socket
     * @param server The chat server instance reference
     */
    public ConnectionThread(String clientName, Socket socket, Server server) {
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

    /**
     * The main loop where user's commands get parsed and executed
     * 
     * @throws IOException If reading a line from client socket fails
     */
    private void commandLoop() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            logger.log("Data received: " + inputLine);
            ICommand command = Protocol.parseData(inputLine);
            if (command instanceof ICommand) {
                server.commandExecute(clientName, command);    
            }
            if (command instanceof QuitCommand) {
                break;
            }
        }
    }

    /**
     * Main method for the connection thread. Gets executed by the system.
     * 
     * @see java.lang.Thread
     */
    public void run() {
        try {
            logger.log("Connection thread created");
            print("Welcome, <" + clientName + ">!");
            server.commandExecute(clientName, new GetHistoryCommand());
            commandLoop();
        } catch (Exception e) {
            logger.log("ConnectionThread.run(): " + e);
        } finally {
            try {
                out.close();
                in.close();
                socket.close();
            } catch (Exception e) {
                logger.log("ConnectionThread.run(): streams disposing failed");
            }
        }
    }

    /**
     * Outputs text message to the user
     * 
     * @param msg Message to show
     */
    public void print(String msg) {
        out.println(msg);
    }

    /**
     * Sets new name for the user
     * 
     * @param clientName New name for the user
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns name of the user
     *
     * @return Name of the user
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Returns this object's text representation
     * 
     * @return Object's text representation
     */
    public String toString() {
        String parentResult = super.toString();
        return "[" + parentResult + "; clientName=" + clientName + "]";
    }
}