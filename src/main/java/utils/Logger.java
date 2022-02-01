package utils;

public class Logger {

    public static void log(String message)
    {
        System.out.println(message);
    }
    public static void log(String message, String value)
    {
        System.out.println(String.format(message, value));
    }

}
