package kevie.commands;

import kevie.Kevie;
import kevie.tasks.Event;
import kevie.tasks.TaskList;

public class EventCommand extends Command {
    public EventCommand() {
        super("event");
    }

    @Override
    public boolean execute(String arg) {
        if (arg == null){
            Kevie.speak("Specify what kind of event you want me to add!");
            return false;
        }

        String description;
        String[] eventArgs;
        Event newEvent;

        try {
            eventArgs = (arg.split(" /from "));
            description = eventArgs[0];
            eventArgs = eventArgs[1].split(" /to ");
            newEvent = new Event(description, eventArgs[0], eventArgs[1]);
        } catch (Exception e){
            Kevie.speak("Syntax is bad! Describe an event, put \" /from\" followed by the start time, " +
                    "then put \" /to\" followed by the end time.");
            return false;
        }

        TaskList.instance.addTask(newEvent);
        Kevie.speak("Good! Adding new event to the list: ");
        Kevie.speak(newEvent.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }
}
