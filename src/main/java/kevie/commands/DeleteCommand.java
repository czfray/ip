package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.*;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        super("delete");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException, TaskNoException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }

        int deleteNo = -1;

        try {
            deleteNo = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new TaskNoParseException();
        }

        if (deleteNo > TaskList.instance.getLength()) {
            throw new TaskNoExceedException(deleteNo, TaskList.instance.getLength());
        } else if (deleteNo < 1) {
            throw new TaskNoNonPosException();
        }

        Task deleteTask = TaskList.instance.getTask(deleteNo - 1);
        TaskList.instance.deleteTask(deleteNo - 1);
        TaskList.instance.save();
        Kevie.speak("Can! I have deleted the following task: ");
        Kevie.speak(deleteTask.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public void help() {
        super.help();
        Kevie.speak("To see which number correspond to your task, do \"list\".", true);
    }

    @Override
    public String syntax() {
        return "delete [Task No.]";
    }

    @Override
    public String example() {
        return "delete 3";
    }
}
