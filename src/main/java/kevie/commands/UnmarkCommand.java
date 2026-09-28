package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.*;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand() {
        super("unmark");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException, TaskNoException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }

        int unmarkNo = -1;

        try {
            unmarkNo = Integer.parseInt(arg);
        } catch(NumberFormatException e) {
            throw new TaskNoParseException();
        }

        if (unmarkNo > TaskList.instance.getLength()) {
            throw new TaskNoExceedException(unmarkNo, TaskList.instance.getLength());
        } else if (unmarkNo < 1) {
            throw new TaskNoNonPosException();
        }

        Task unmarkedTask = TaskList.instance.getTask(unmarkNo - 1);
        if (!unmarkedTask.isDone()){
            Kevie.speak("Task " + (unmarkNo) + " is marked as not done already:");
            Kevie.speak(unmarkedTask.toString(), true);
            return false;
        }
        unmarkedTask.setDone(false);
        TaskList.instance.save();
        Kevie.speak("Ok! I have marked a task as undone:");
        Kevie.speak(unmarkedTask.toString(), true);
        return false;
    }

    @Override
    public void help() {
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
