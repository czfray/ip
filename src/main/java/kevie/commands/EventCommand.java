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
            Kevie.speak("Specify a name and descriptions of your event to me!");
            help();
            return false;
        }

        String description;
        String[] eventArgs;
        Event newEvent;

        try {
            eventArgs = arg.split(" /from ");
            description = eventArgs[0];
            eventArgs = eventArgs[1].split(" /to ");
        } catch (Exception e) {
            Kevie.speak("Please make sure to indicate name, start time and end time of the event.");
            help();
            return false;
        }

        try {
            newEvent = new Event(description, eventArgs[0], eventArgs[1]);
        } catch (Exception e) {
            Kevie.speak("Syntax is bad! You have to tell me when the event ends!");
            help();
            return false;
        }

        TaskList.instance.addTask(newEvent);
        Kevie.speak("Good! Adding new event to the list: ");
        Kevie.speak(newEvent.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "event [Name] /from [Start Time] /to [End Time]";
    }

    @Override
    public String example() {
        return "event CS2113 team project meeting /from Sep 1st 6pm /to Sep 1st 8pm";
    }
}
