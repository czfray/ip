package kevie.commands;

import kevie.Kevie;
import kevie.tasks.Deadline;
import kevie.tasks.TaskList;

public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        super("deadline");
    }

    @Override
    public boolean execute(String arg) {
        if (arg == null){
            Kevie.speak("Specify a name and details for your deadline to me!");
            help();
            return false;
        }

        int index = arg.indexOf("/by");
        if (index != -1 && arg.substring(0, index).trim().isEmpty()) {
            Kevie.speak("What is the name of your deadline? You have to tell me!");
            help();
            return false;
        }

        String[] deadlineArgs = arg.split(" /by ");
        if (deadlineArgs.length < 2){

            Kevie.speak("Syntax is bad! You have not specified a due time for your deadline.");
            help();
            return false;
        }

        Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        TaskList.instance.addTask(newDeadline);
        Kevie.speak("Okay I added new deadline to the list: ");
        Kevie.speak(newDeadline.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "deadline [Name] /by [Due Time]";
    }

    @Override
    public String example() {
        return "deadline CS2113 individual project /by 5th Sep 2359";
    }

}
