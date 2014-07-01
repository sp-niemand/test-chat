import chat.Server;
import chat.Logger;

class TestAssignment {
    static final int CODE_WRONG_ARGUMENTS = 1;

    public static void main(String[] args)
    {
        if (args.length < 1) {
            System.out.println("Wrong arguments number given.");
            System.exit(TestAssignment.CODE_WRONG_ARGUMENTS);
        }

        int port = Integer.parseInt(args[0]);
        Logger logger = new Logger();
        Server server = new Server(port, logger);

        try {
            server.run();
        } catch (Exception e) {
            System.out.println("Exception in main thread");
            System.out.println(e.getMessage());
        }
    }
}