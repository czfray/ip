package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.*;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class MarkCommand extends TaskCommand {
    public MarkCommand(UserInterface ui, TaskList taskList) {
        super("mark", ui, taskList);
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException, TaskNoException {
        if (arg == null){
            throw new CmdSynNoArgException(this, ui);
        }

        int markNo = -1;

        try {
            markNo = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new TaskNoParseException(ui);
        }

        if (markNo > taskList.getLength()) {
            throw new TaskNoExceedException(markNo, taskList.getLength(), ui);
        } else if (markNo < 1) {
            throw new TaskNoNonPosException(ui);
        }

        Task markedTask = taskList.getTask(markNo - 1);
        if (markedTask.isDone()){
            ui.botSpeak("Task " + (markNo) + " is marked as done already:");
            ui.botSpeak(markedTask.toString(), true);
            return false;
        }
        markedTask.setDone(true);
        taskList.save(ui);
        ui.botSpeak("Ok! I have marked a task as done: ");
        ui.botSpeak(markedTask.toString(), true);
        return false;
    }

    @Override
    public void help() {
        super.help();
        ui.botSpeak("To see which number correspond to your task, do \"list\".", true);
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
