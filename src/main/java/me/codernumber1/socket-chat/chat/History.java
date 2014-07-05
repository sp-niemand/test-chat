/**
 * History class
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
package me.codernumber1.socket_chat.chat;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import me.codernumber1.socket_chat.chat.Logger;
import me.codernumber1.socket_chat.chat.exception.HistoryException;

/**
 * This class encapsulates operations with the chat history
 *
 * @author Dmitri Cherepovski <codernumber1@gmail.com>
 */
public class History {
    public static final int HISTORY_MESSAGES_COUNT = 30;
    public static final String TABLE_NAME = "message";
    public static final String DB_FILE_NAME = "history.db";

    private Connection connection;

    /**
     * Constructor 
     * 
     * @throws HistoryException If startup DB operations fail
     */
    public History() throws HistoryException {
        try {
            Class.forName("org.sqlite.JDBC");    
        } catch (ClassNotFoundException e) {
            throw new HistoryException("Sqlite driver for JDBC not found", e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);
        } catch (SQLException e) {
            Logger.instance().log(e);
            throw new HistoryException("DB connection failed", e);
        }
        this.connection = connection;
    }

    /**
     * Ensures existence of the DB table for history operations
     * 
     * @throws HistoryException If the table existence check or its creation fails
     */
    public void ensureTable() throws HistoryException {
        try { 
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='" + TABLE_NAME + "'"
            );
            if (rs.next()) {
                return;
            }

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                        + "id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
                        + "name STRING,"
                        + "message STRING,"
                        + "timestamp INTEGER"
                        + ")";
            statement.executeUpdate(sql);
            statement.executeUpdate(
                "CREATE INDEX IF NOT EXISTS timestamp_index ON " + TABLE_NAME + " (timestamp)"
            );            
        } catch (SQLException e) {
            Logger.instance().log(e);
            throw new HistoryException("DB table existence ensurance failed", e);
        }
    }

    /**
     * Returns last messages from the chat history
     *
     * @return An array of messages, prepared to be output to the user
     */
    public String[] getLastMessages() throws HistoryException {
        return getLastMessages(HISTORY_MESSAGES_COUNT);
    }

    /**
     * Returns last messages from the chat history
     *
     * @param count Maximum number of messages to be returned
     * 
     * @return An array of messages, prepared to be output to the user
     */
    public String[] getLastMessages(int count) throws HistoryException {
        ArrayList<String> lineList = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                "SELECT name, message" 
                + " FROM " + TABLE_NAME 
                + " ORDER BY timestamp DESC" 
                + " LIMIT " + count
            );

            while (rs.next()) {
                lineList.add(MessageFormatter.getMessageText(rs.getString("name"), rs.getString("message")));
            }
        } catch (SQLException e) {
            Logger.instance().log(e);
            throw new HistoryException("History storage fetch failed", e);
        }

        int lineListSize = lineList.size();
        if (lineListSize != 0) {
            String[] result = new String[lineListSize];
            int i = lineListSize - 1;
            for (String line : lineList) {
                result[i --] = line;
            }
            return result;
        } else {
            return new String[0];
        }
    }

    /**
     * Saves the message
     * 
     * @param clientName User's name 
     * @param message Message text
     * 
     * @throws HistoryException If saving fails
     */
    public void addMessage(
        String clientName, 
        String message
    ) throws HistoryException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " " 
                    + "(name, message, timestamp) " 
                    + "VALUES (?, ?, ?)"
            );
            statement.setString(1, clientName);
            statement.setString(2, message);
            statement.setLong(3, (new java.util.Date()).getTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.instance().log(e);
            throw new HistoryException("Message adding to history storage failed", e);
        }
    }
}