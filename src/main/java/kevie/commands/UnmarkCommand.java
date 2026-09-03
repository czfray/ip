package kevie.commands;

import kevie.Kevie;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand() {
        super("unmark");
    }

    @Override
    public boolean execute(String arg) {
        int unmarkno = -1;

        try {
            unmarkno = Integer.parseInt(arg);
        }
        catch(NumberFormatException e) {
            Kevie.speak("I cannot unmark anything if you dont give me a number lol.");
            return false;
        }

        if (unmarkno > TaskList.instance.getLength())
        {
            Kevie.speak("There are only " + TaskList.instance.getLength() + " tasks, yet you want to unmark task "
                    + unmarkno + "? Try again bro.");
            return false;
        }
        else if (unmarkno < 1)
        {
            Kevie.speak("Your task number to be undone must be positive ahh!!!");
            return false;
        }
        Task unmarkedTask = TaskList.instance.getTask(unmarkno - 1);
        unmarkedTask.setDone(false);
        Kevie.speak("Ok! I have marked a task as undone:");
        Kevie.speak(unmarkedTask.toString(), true);
        return false;
    }
}
