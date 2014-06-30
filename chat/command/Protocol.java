package chat.command;

public class Protocol {
    public static ICommand parseData(String data)
    {
        return new LoginCommand("testName");
    }
}