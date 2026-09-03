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
            Kevie.speak("Specify what deadline you want me to set!");
            return false;
        }

        String[] deadlineArgs = arg.split(" /by ");
        if (deadlineArgs.length < 2){
            Kevie.speak("Syntax is bad! Describe a deadline, then put \" /by\" followed by the end time.");
            return false;
        }

        Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        TaskList.instance.addTask(newDeadline);
        Kevie.speak("Okay I added new deadline to the list: ");
        Kevie.speak(newDeadline.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }
}
