import java.util.Scanner;

public class Kevie {

    public static final String KEVIE_PREFIX = "[Kevie]";
    public static final String USER_PREFIX = "[You]";
    public static final String IDENTATION = "  ";
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

        Task[] itemList = new Task[LIST_MAX_LEN];
        int itemListLen = 0;

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.print(USER_PREFIX + " ");
            String input = scanner.nextLine();
            String[] inputArgs = input.split(" ");

            if (inputArgs[0].equals("bye")) break;

            if (inputArgs[0].equals("list"))
            {
                kevieTalk("Ok my guy, here is your list:");
                for (int i = 0; i < itemListLen; i++) {
                    System.out.println(IDENTATION + (i+1) + ". " + itemList[i].toString());
                }
                continue;
            }

            if (inputArgs[0].equals("mark") && inputArgs.length == 2)
            {
                try {
                    int markNo = Integer.parseInt(inputArgs[1]);
                    if (markNo > itemListLen)
                    {
                        kevieTalk("There are only " + itemListLen + " tasks, yet you want to mark task " + markNo + " as done? Try again bro.");
                        continue;
                    }
                    else if (markNo < 1)
                    {
                        kevieTalk("Your task number to be marked done must be positive ahh!!!");
                        continue;
                    }

                    Task markedTask = itemList[markNo - 1];

                    markedTask.setDone(true);
                    kevieTalk("Ok! I have marked a task as done: ");
                    System.out.println(IDENTATION + markedTask.toString());
                    continue;
                }
                catch(NumberFormatException e) {}
            }

            if (inputArgs[0].equals("unmark") && inputArgs.length == 2)
            {
                try {
                    int markNo = Integer.parseInt(inputArgs[1]);
                    if (markNo > itemListLen)
                    {
                        kevieTalk("There are only " + itemListLen + " tasks, yet you want to unmark task " + markNo + "? Try again bro.");
                        continue;
                    }
                    else if (markNo < 1)
                    {
                        kevieTalk("Your task number to be undone must be positive ahh!!!");
                        continue;
                    }

                    Task markedTask = itemList[markNo - 1];

                    markedTask.setDone(false);
                    kevieTalk("Ok! I have marked a task as undone:");
                    System.out.println(IDENTATION + markedTask.toString());
                    continue;
                }
                catch(NumberFormatException e) {}
            }

            Task newTask = new Task(input);
            itemList[itemListLen] = newTask;
            itemListLen++;
            kevieTalk("Ok! I added a new task:");
            System.out.println(IDENTATION + newTask.toString());
        }

        kevieTalk("Bye bye! See you later!");
    }
}
