package chat;

public class Logger {

    private static Logger instance;

    private Logger()
    {
    }

    public static Logger instance()
    {
        return instance == null
            ? instance = new Logger() 
            : instance;
    }

    public void log(Object obj)
    {
        System.out.println(obj.toString() + "\n");
    }
}