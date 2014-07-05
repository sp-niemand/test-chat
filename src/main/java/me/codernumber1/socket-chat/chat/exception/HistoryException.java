package me.codernumber1.socket_chat.chat.exception;

public class HistoryException extends Exception {
    public HistoryException(String msg)   
    {
        super(msg);
    }

    public HistoryException(String msg, Throwable reason)   
    {
        super(msg, reason);
    }
}