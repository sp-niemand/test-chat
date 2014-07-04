package chat.exception;

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