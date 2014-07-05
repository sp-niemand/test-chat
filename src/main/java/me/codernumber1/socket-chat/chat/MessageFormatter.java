/**
 * MessageFormatter class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat;

/**
 * This class contains methods which format text messages to be output to the user
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class MessageFormatter {
    /**
     * Returns formatted text message for all users in the chat
     * 
     * @param clientName Sender's name
     * @param message Message text
     * 
     * @return Formatted text message
     */
    public static String getMessageText(String clientName, String message) {
        return clientName + ": " + message;
    }

    /**
     * Returns formatted text message for a single user in the chat
     * 
     * @param clientName Sender's name
     * @param message Message text
     * 
     * @return Formatted text message
     */
    public static String getPrivateMessageText(String clientName, String message) {
        return clientName + ":<private>: " + message;
    }
}