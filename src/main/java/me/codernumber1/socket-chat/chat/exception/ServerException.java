/**
 * ServerException class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat.exception;

/**
 * ServerException class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class ServerException extends Exception {
    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(String msg, Throwable reason) {
        super(msg, reason);
    }
}