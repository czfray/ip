package kevie.commands;

import kevie.Kevie;
import kevie.tasks.Task;
import kevie.tasks.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        super("delete");
    }

    @Override
    public boolean execute(String arg) {
        int deleteNo = -1;

        try {
            deleteNo = Integer.parseInt(arg);

        } catch (Exception e) {
            Kevie.speak("Give me the NUMBER of the task you want me to delete!");
            help();
            return false;
        }

        if (deleteNo > TaskList.instance.getLength())
        {
            Kevie.speak("There are only " + TaskList.instance.getLength() + " tasks, yet you want to delete task "
                    + deleteNo + "? Try again.");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
        }
        else if (deleteNo < 1)
        {
            Kevie.speak("Your task number to be deleted must be positive!!!");
            Kevie.speak("To see which number correspond to your task, do \"list\".", true);
            return false;
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
    protected void help() {
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
