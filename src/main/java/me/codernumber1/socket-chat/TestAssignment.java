package me.codernumber1.socket_chat;

import me.codernumber1.socket_chat.chat.Server;
import me.codernumber1.socket_chat.chat.Logger;

class TestAssignment {
    static final int CODE_WRONG_ARGUMENTS = 1;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Wrong arguments number given.");
            System.exit(TestAssignment.CODE_WRONG_ARGUMENTS);
        }

        int port = Integer.parseInt(args[0]);

        try {
            Server server = new Server(port);
            server.run();
        } catch (Exception e) {
            System.out.println("Exception in main thread: " + e);
            e.printStackTrace();
        }
    }
}