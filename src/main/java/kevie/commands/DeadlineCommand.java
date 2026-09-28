package kevie.commands;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.CmdSynNoArgException;
import kevie.exceptions.CmdSyntaxException;
import kevie.tasks.Deadline;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class DeadlineCommand extends TaskCommand {

    private final String dueSeperator = "/by";

    public DeadlineCommand(UserInterface ui, TaskList taskList) {
        super("deadline", ui, taskList);
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException {
        if (arg == null){
            throw new CmdSynNoArgException(this, ui);
        }

        int index = arg.indexOf(dueSeperator);
        if (index != -1 && arg.substring(0, index).trim().isEmpty()) {
            throw new CmdSyntaxException("Deadline description not given", this, ui);
        }

        String[] deadlineArgs = arg.split(" " + dueSeperator + " ");
        if (deadlineArgs.length < 2){
            throw new CmdSyntaxException("Deadline due time not given", this, ui);
        }

        Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.addTask(newDeadline);
        taskList.save(ui);
        ui.botSpeak("Okay I added new deadline to the list: ");
        ui.botSpeak(newDeadline.toString(), true);
        ui.botSpeak("You now have " + taskList.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "deadline [Description] " + dueSeperator + " [Due Time]";
    }

    @Override
    public String example() {
        return "deadline CS2113 individual project " + dueSeperator + " 5th Sep 2359";
    }

}
