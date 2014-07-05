/**
 * Logger class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat;

/**
 * Singleton class used to log system messages for the chat
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class Logger {

    private static Logger instance;

    /**
     * Unaccessible constructor
     */
    private Logger() {
    }

    /**
     * Singleton instance accessor
     * 
     * @return Logger instance
     */
    public static Logger instance() {
        return instance == null
            ? instance = new Logger() 
            : instance;
    }

    /**
     * Logs the object string representation
     * 
     * @param obj Object to be logged
     */
    public void log(Object obj) {
        System.out.println(obj.toString() + "\n");
    }
}