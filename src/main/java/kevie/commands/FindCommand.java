package kevie.commands;

import kevie.Kevie;
import kevie.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{

    public FindCommand() {
        super("find");
    }

    @Override
    public boolean execute(String arg) {
        if (arg == null){
            Kevie.speak("Specify me a keyword for me to find! ");
            help();
            return false;
        }

        ArrayList<Integer> taskIndexes = TaskList.instance.keywordFindTasks(arg.trim());

        if (taskIndexes.size() <= 0){
            Kevie.speak("There are no task with keyword \"" + arg.trim() + "\" in your list.");
            return false;
        }

        Kevie.speak("Here are the tasks with the keyword \"" + arg.trim() + "\" in your list: ");
        for (int i : taskIndexes){
            Kevie.speak((i + 1) + ". " + TaskList.instance.getTask(i).toString(), true);
        }
        return false;
    }

    @Override
    public String syntax() {
        return "find [String]";
    }

    @Override
    public String example() {
        return "find CS2113";
    }
}
