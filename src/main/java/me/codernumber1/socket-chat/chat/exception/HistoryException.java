/**
 * HistoryException class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.exception;

/**
 * HistoryException class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class HistoryException extends Exception {
    public HistoryException(String msg) {
        super(msg);
    }

    public HistoryException(String msg, Throwable reason) {
        super(msg, reason);
    }
}