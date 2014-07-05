package me.codernumber1.socket_chat.chat.exception;

import java.net.*;

public class SocketException extends Exception {
    private Socket socket;

    public Socket getSocket()
    {
        return socket;
    }

    public SocketException(Socket socket) 
    {
        this.socket = socket;
    }
}