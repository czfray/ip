import java.util.Locale;
import java.util.Scanner;

public class Kevie {

    public static final String BANNER = """
                ====================================
                ██╗  ██╗███████╗██╗   ██╗██╗███████╗
                ██║ ██╔╝██╔════╝██║   ██║██║██╔════╝
                █████╔╝ █████╗  ██║   ██║██║█████╗
                ██╔═██╗ ██╔══╝  ╚██╗ ██╔╝██║██╔══╝
                ██║  ██╗███████╗ ╚████╔╝ ██║███████╗
                ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝
                ====================================""";

    public static final String PREFIX_BOT = "[Kevie]";
    public static final String PREFIX_USER = "[You]";
    public static final String PREFIX_INDENT = "  ";
    public static final int LIST_MAX_LEN = 100;

    public static void botSay(String msg) {
        System.out.println(PREFIX_BOT + " " + msg);
    }

    public static void main(String[] args) {
        System.out.println(BANNER);
        botSay("Hey, what's up!");
        botSay("Anything you want to get done today?");

        TaskList taskList = new TaskList(LIST_MAX_LEN);

        Scanner scanner = new Scanner(System.in);

        inputLoop:
        while(true)
        {
            System.out.print(PREFIX_USER + " ");
            String input = scanner.nextLine();
            String[] inputArgs = input.split(" ", 2);

            switch(inputArgs[0].toLowerCase()){
                case "bye":
                    break inputLoop;
                case "list":
                    botSay("Ok my guy, here is your list:");
                    taskList.printAll(PREFIX_INDENT);
                    continue;
                case "mark":
                    try {
                        int markNo = Integer.parseInt(inputArgs[1]);
                        if (markNo > taskList.getLength())
                        {
                            botSay("There are only " + taskList.getLength() + " tasks, yet you want to mark task " + markNo
                                    + " as done? Try again bro.");
                            continue;
                        }
                        else if (markNo < 1)
                        {
                            botSay("Your task number to be marked done must be positive ahh!!!");
                            continue;
                        }

                        Task markedTask = taskList.getTask(markNo - 1);
                        markedTask.setDone(true);
                        botSay("Ok! I have marked a task as done: ");
                        System.out.println(PREFIX_INDENT + markedTask.toString());
                        continue;

                    } catch (Exception e) {
                        botSay("I cannot mark anything if you dont give me a number lol.");
                        continue;
                    }
                case "unmark":
                    try {
                        int markNo = Integer.parseInt(inputArgs[1]);
                        if (markNo > taskList.getLength())
                        {
                            botSay("There are only " + taskList.getLength() + " tasks, yet you want to unmark task " + markNo
                                    + "? Try again bro.");
                            continue;
                        }
                        else if (markNo < 1)
                        {
                            botSay("Your task number to be undone must be positive ahh!!!");
                            continue;
                        }

                        Task markedTask = taskList.getTask(markNo - 1);
                        markedTask.setDone(false);
                        botSay("Ok! I have marked a task as undone:");
                        System.out.println(PREFIX_INDENT + markedTask.toString());
                        continue;
                    }
                    catch(NumberFormatException e) {
                        botSay("I cannot unmark anything if you dont give me a number lol.");
                        continue;
                    }
                default:
                    Task newTask = new Task(input);
                    taskList.addTask(newTask);
                    botSay("Ok! I added a new task:");
                    System.out.println(PREFIX_INDENT + newTask.toString());
            }
        }
        botSay("Bye bye! See you later!");
    }
}
