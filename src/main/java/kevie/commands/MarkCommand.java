package kevie.commands;

import kevie.Kevie;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class MarkCommand extends Command {
    public MarkCommand() {
        super("mark");
    }

    @Override
    public boolean execute(String arg) {
        int markNo = -1;

        try {
            markNo = Integer.parseInt(arg);

        } catch (Exception e) {
            Kevie.speak("I cannot mark anything if you dont give me a number lol.");
            help();
            return false;
        }

        if (markNo > TaskList.instance.getLength())
        {
            Kevie.speak("There are only " + TaskList.instance.getLength() + " tasks, yet you want to mark task "
                    + markNo + " as done? Try again bro.");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
        }
        else if (markNo < 1)
        {
            Kevie.speak("Your task number to be marked done must be positive ahh!!!");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
        }

        Task markedTask = TaskList.instance.getTask(markNo - 1);
        if (markedTask.isDone()){
            Kevie.speak("Task " + (markNo) + " is marked as done already.");
            Kevie.speak(markedTask.toString(), true);
            return false;
        }
        markedTask.setDone(true);
        Kevie.speak("Ok! I have marked a task as done: ");
        Kevie.speak(markedTask.toString(), true);
        return false;
    }

    @Override
    protected void help() {
        super.help();
        Kevie.speak("To see which number correspond to your task, do \"list\".", true);
    }

    @Override
    public String syntax() {
        return "mark [Task No.]";
    }

    @Override
    public String example() {
        return "mark 3";
    }
}
