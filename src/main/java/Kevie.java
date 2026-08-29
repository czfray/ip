import java.util.Scanner;

public class Kevie {

    public static final String KEVIE_PREFIX = "[Kevie]";
    public static final String USER_PREFIX = "[You]";
    public static final int LIST_MAX_LEN = 100;

    public static void kevieTalk(String msg)
    {
        System.out.println(KEVIE_PREFIX + " " + msg);
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

        String[] itemList = new String[LIST_MAX_LEN];
        int itemListLen = 0;

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.print(USER_PREFIX + " ");
            String input = scanner.nextLine();

            if (input.equals("bye")) break;

            if (input.equals("list"))
            {
                kevieTalk("Here is your list of items: ");
                for (int i = 0; i < itemListLen; i++) {
                    System.out.println("  " + (i+1) + ". " + itemList[i]);
                }
                continue;
            }

            itemList[itemListLen] = input;
            itemListLen++;
            kevieTalk("Added \"" + input + "\" to your list of items!");
        }

        kevieTalk("Bye bye! See you later!");
    }
}
