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
        int unmarkNo = -1;

        try {
            unmarkNo = Integer.parseInt(arg);
        }
        catch(NumberFormatException e) {
            Kevie.speak("I cannot unmark anything if you dont give me a number lol.");
            help();
            return false;
        }

        if (unmarkNo > TaskList.instance.getLength())
        {
            Kevie.speak("There are only " + TaskList.instance.getLength() + " tasks, yet you want to unmark task "
                    + unmarkNo + "? Try again bro.");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
        }
        else if (unmarkNo < 1)
        {
            Kevie.speak("Your task number to be undone must be positive ahh!!!");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
        }
        Task unmarkedTask = TaskList.instance.getTask(unmarkNo - 1);
        if (!unmarkedTask.isDone()){
            Kevie.speak("Task " + (unmarkNo) + " is marked as not done already.");
            Kevie.speak(unmarkedTask.toString(), true);
            return false;
        }
        unmarkedTask.setDone(false);
        Kevie.speak("Ok! I have marked a task as undone:");
        Kevie.speak(unmarkedTask.toString(), true);
        return false;
    }

    @Override
    protected void help() {
        super.help();
        Kevie.speak("To see which number correspond to your task, do \"list\".", true);
    }

    @Override
    public String syntax() {
        return "unmark [Task No.]";
    }

    @Override
    public String example() {
        return "unmark 1";
    }
}
