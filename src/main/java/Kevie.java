import java.util.Scanner;

public class Kevie {

    public static final String keviePrefix = "[Kevie]";
    public static final String userPrefix = "[You]";

    public static void kevieTalk(String msg)
    {
        System.out.println(keviePrefix + " " + msg);
    }

    public static void main(String[] args) {
        String banner = """
                ====================================
                ██╗  ██╗███████╗██╗   ██╗██╗███████╗
                ██║ ██╔╝██╔════╝██║   ██║██║██╔════╝
                █████╔╝ █████╗  ██║   ██║██║█████╗
                ██╔═██╗ ██╔══╝  ╚██╗ ██╔╝██║██╔══╝
                ██║  ██╗███████╗ ╚████╔╝ ██║███████╗
                ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝
                ====================================""";
        System.out.println(banner);

        kevieTalk("Hey, what's up!");
        kevieTalk("Anything you want to get done today?");

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.print(userPrefix + " ");
            String input = scanner.nextLine();

            if (input.equals("bye")) break;
            kevieTalk(input);
        }

        kevieTalk("Bye bye! See you later!");
    }
}
