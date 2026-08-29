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

    public static void botSay(String msg, boolean identOnly) {
        if (!identOnly) System.out.println(PREFIX_BOT + " " + msg);
        else System.out.println(PREFIX_INDENT + " " + msg);
    }

    public static void botSay(String msg) {
        botSay(msg, false);
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
                    break;
                case "mark":
                    int markNo = -1;

                    try {
                        markNo = Integer.parseInt(inputArgs[1]);

                    } catch (Exception e) {
                        botSay("I cannot mark anything if you dont give me a number lol.");
                        break;
                    }

                    if (markNo > taskList.getLength())
                    {
                        botSay("There are only " + taskList.getLength() + " tasks, yet you want to mark task "
                                + markNo + " as done? Try again bro.");
                        break;
                    }
                    else if (markNo < 1)
                    {
                        botSay("Your task number to be marked done must be positive ahh!!!");
                        break;
                    }

                    Task markedTask = taskList.getTask(markNo - 1);
                    markedTask.setDone(true);
                    botSay("Ok! I have marked a task as done: ");
                    botSay(markedTask.toString(), true);
                    break;
                case "unmark":
                    int unmarkno = -1;

                    try {
                        unmarkno = Integer.parseInt(inputArgs[1]);
                    }
                    catch(NumberFormatException e) {
                        botSay("I cannot unmark anything if you dont give me a number lol.");
                        break;
                    }

                    if (unmarkno > taskList.getLength())
                    {
                        botSay("There are only " + taskList.getLength() + " tasks, yet you want to unmark task "
                                + unmarkno + "? Try again bro.");
                        break;
                    }
                    else if (unmarkno < 1)
                    {
                        botSay("Your task number to be undone must be positive ahh!!!");
                        break;
                    }
                    Task unmarkedTask = taskList.getTask(unmarkno - 1);
                    unmarkedTask.setDone(false);
                    botSay("Ok! I have marked a task as undone:");
                    botSay(unmarkedTask.toString(), true);
                    break;
                case "todo":
                    if (inputArgs.length < 2){
                        botSay("You need to tell me what you want to do bro!");
                        break;
                    }
                    Todo newTodo = new Todo(inputArgs[1]);
                    taskList.addTask(newTodo);
                    botSay("Alright I added new todo to the list: ");
                    botSay(newTodo.toString(), true);
                    botSay("You now have " + taskList.getLength() + " tasks.", true);
                    break;
                case "deadline":
                    if (inputArgs.length < 2){
                        botSay("Specify what deadline you want me to set!");
                        break;
                    }

                    String[] deadlineArgs = inputArgs[1].split(" /by ");
                    if (deadlineArgs.length < 2){
                        botSay("Syntax is bad! Describe a deadline, then put \" /by\" followed by the end time.");
                        break;
                    }

                    Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    taskList.addTask(newDeadline);
                    botSay("Okay I added new deadline to the list: ");
                    botSay(newDeadline.toString(), true);
                    botSay("You now have " + taskList.getLength() + " tasks.", true);
                    break;
                case "event":
                    if (inputArgs.length < 2){
                        botSay("Specify what kind of event you want me to add!");
                        break;
                    }

                    String description;
                    String[] eventArgs;
                    Event newEvent;

                    try {
                        eventArgs = (inputArgs[1].split(" /from "));
                        description = eventArgs[0];
                        eventArgs = eventArgs[1].split(" /to ");
                        newEvent = new Event(description, eventArgs[0], eventArgs[1]);
                    } catch (Exception e){
                        botSay("Syntax is bad! Describe an event, put \" /from\" followed by the start time, " +
                                "then put \" /to\" followed by the end time.");
                        break;
                    }

                    taskList.addTask(newEvent);
                    botSay("Good! Adding new event to the list: ");
                    botSay(newEvent.toString(), true);
                    botSay("You now have " + taskList.getLength() + " tasks.", true);
                    break;
                default:
                    botSay("heh I dun get you bro, type something I understand.");
                    break;
            }
        }
        botSay("Bye bye! See you later!");
    }
}
