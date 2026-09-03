package kevie;

import kevie.commands.*;

import java.util.Scanner;

public class Kevie {

    private static final String BANNER = """
                ====================================
                ██╗  ██╗███████╗██╗   ██╗██╗███████╗
                ██║ ██╔╝██╔════╝██║   ██║██║██╔════╝
                █████╔╝ █████╗  ██║   ██║██║█████╗
                ██╔═██╗ ██╔══╝  ╚██╗ ██╔╝██║██╔══╝
                ██║  ██╗███████╗ ╚████╔╝ ██║███████╗
                ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝
                ====================================""";

    private static final String PREFIX_BOT = "[Kevie]";
    private static final String PREFIX_USER = "[You]";
    private static final String PREFIX_INDENT = "  ";

    public static void speak(String msg, boolean indentOnly) {
        if (!indentOnly) System.out.println(PREFIX_BOT + " " + msg);
        else System.out.println(PREFIX_INDENT + " " + msg);
    }

    public static void speak(String msg) {
        speak(msg, false);
    }

    public static void main(String[] args) {
        System.out.println(BANNER);
        speak("Hey, what's up!");
        speak("Anything you want to get done today?");

        new ByeCommand();
        new ListCommand(PREFIX_INDENT);
        new TodoCommand();
        new DeadlineCommand();
        new EventCommand();
        new MarkCommand();
        new UnmarkCommand();

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.print(PREFIX_USER + " ");
            String input = scanner.nextLine();
            if (Command.scan(input)) break;
        }
    }
}
