package me.codernumber1.socket_chat.chat.exception;

public class ServerException extends Exception {
    public ServerException(String msg)   
    {
        super(msg);
    }

    public ServerException(String msg, Throwable reason)   
    {
        super(msg, reason);
    }
}