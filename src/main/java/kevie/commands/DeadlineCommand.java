package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.CmdSynNoArgException;
import kevie.exceptions.CmdSyntaxException;
import kevie.tasks.Deadline;
import kevie.tasks.TaskList;

public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        super("deadline");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }

        int index = arg.indexOf("/by");
        if (index != -1 && arg.substring(0, index).trim().isEmpty()) {
            throw new CmdSyntaxException("Deadline description not given", this);
        }

        String[] deadlineArgs = arg.split(" /by ");
        if (deadlineArgs.length < 2){
            throw new CmdSyntaxException("Deadline due time not given", this);
        }

        Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        TaskList.instance.addTask(newDeadline);
        TaskList.instance.save();
        Kevie.speak("Okay I added new deadline to the list: ");
        Kevie.speak(newDeadline.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "deadline [Description] /by [Due Time]";
    }

    @Override
    public String example() {
        return "deadline CS2113 individual project /by 5th Sep 2359";
    }

}
