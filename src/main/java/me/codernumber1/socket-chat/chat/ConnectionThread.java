package chat;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chat.command.Protocol;
import chat.command.ICommand;
import chat.command.QuitCommand;
import chat.command.GetHistoryCommand;

public class ConnectionThread extends Thread {
    private Socket socket;
    private Server server;
    private Logger logger = Logger.instance();
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

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

    private void commandLoop() throws IOException
    {
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

    public void run()
    {
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

    public void print(String msg)
    {
        out.println(msg);
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }

    public String toString()
    {
        String parentResult = super.toString();
        return "[" + parentResult + "; clientName=" + clientName + "]";
    }
}