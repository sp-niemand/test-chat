package chat;

public class MessageFormatter {
    public static String getMessageText(String clientName, String message)
    {
        return clientName + ": " + message;
    }

    public static String getPrivateMessageText(String clientName, String message)
    {
        return clientName + ":<private>: " + message;
    }
}