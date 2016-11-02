package javacodingguidelines.noncompliant;



import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/3/11.
 */
public class Password {
    private BufferedReader reader;

    public static void main(String[] args) throws IOException {
//        Console c = System.console();
//        if (c == null) {
//            System.err.println("No console");
//            System.exit(1);
//
//        }
//
//        String username = c.readLine("Enter your user name:");
//        String password = c.readLine("Enter your password:");

        String username = readLine("Enter your Username:");
        String password = readLine("Enter your Password:");

        if (!verify(username, password)) {
            throw new SecurityException("Invalid Credentials");

        }
    }

    private static boolean verify(String username, String password) {
        return true;
    }

    private static String readLine(String format,Object... args) throws IOException {
        if (System.console() != null) {
            return System.console().readLine(format, args);
        }
        System.out.println(String.format(format,args));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private static String readPassword(String format,Object... args) throws IOException {
        if (System.console() != null) {
            return System.console().readLine(format, args);
        }
        System.out.println(String.format(format,args));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
