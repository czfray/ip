package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.*;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class MarkCommand extends Command {
    public MarkCommand() {
        super("mark");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException, TaskNoException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }

        int markNo = -1;

        try {
            markNo = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new TaskNoParseException();
        }

        if (markNo > TaskList.instance.getLength()) {
            throw new TaskNoExceedException(markNo, TaskList.instance.getLength());
        } else if (markNo < 1) {
            throw new TaskNoNonPosException();
        }

        Task markedTask = TaskList.instance.getTask(markNo - 1);
        if (markedTask.isDone()){
            Kevie.speak("Task " + (markNo) + " is marked as done already:");
            Kevie.speak(markedTask.toString(), true);
            return false;
        }
        markedTask.setDone(true);
        TaskList.instance.save();
        Kevie.speak("Ok! I have marked a task as done: ");
        Kevie.speak(markedTask.toString(), true);
        return false;
    }

    @Override
    public void help() {
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
