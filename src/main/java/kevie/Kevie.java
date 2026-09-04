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
    private static final String PREFIX_INDENT = "        ";

    public static void speak(String msg, boolean indentOnly) {
        if (!indentOnly) System.out.println(PREFIX_BOT + " " + msg);
        else System.out.println(PREFIX_INDENT + msg);
    }

    public static void speak(String msg) {
        speak(msg, false);
    }

    private static void initCommands() {
        new ByeCommand();
        new HelpCommand();
        new ListCommand();
        new TodoCommand();
        new DeadlineCommand();
        new EventCommand();
        new MarkCommand();
        new UnmarkCommand();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        initCommands();

        System.out.println(BANNER);
        speak("Hey, what's up!");
        speak("Anything you want to get done today?");

        while(true)
        {
            System.out.print(PREFIX_USER + " ");
            String input = scanner.nextLine();
            if (Command.scan(input)) break;
        }

    }
}
