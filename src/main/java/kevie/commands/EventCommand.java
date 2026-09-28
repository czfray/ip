package kevie.commands;

import kevie.Kevie;
import kevie.exceptions.CmdSynNoArgException;
import kevie.exceptions.CmdSyntaxException;
import kevie.tasks.Event;
import kevie.tasks.TaskList;

public class EventCommand extends Command {
    public EventCommand() {
        super("event");
    }

    @Override
    public boolean execute(String arg) throws CmdSyntaxException {
        if (arg == null){
            throw new CmdSynNoArgException(this);
        }

        String description;
        String[] eventArgs;
        Event newEvent;

        try {
            eventArgs = arg.split(" /from ");
            description = eventArgs[0];
            eventArgs = eventArgs[1].split(" /to ");
        } catch (Exception e) {
            throw new CmdSyntaxException("Event description, start time or end time not indicated", this);
        }

        try {
            newEvent = new Event(description, eventArgs[0], eventArgs[1]);
        } catch (Exception e) {
            throw new CmdSyntaxException("Event end time not given", this);
        }

        TaskList.instance.addTask(newEvent);
        TaskList.instance.save();
        Kevie.speak("Good! Adding new event to the list: ");
        Kevie.speak(newEvent.toString(), true);
        Kevie.speak("You now have " + TaskList.instance.getLength() + " tasks.", true);
        return false;
    }

    @Override
    public String syntax() {
        return "event [Description] /from [Start Time] /to [End Time]";
    }

    @Override
    public String example() {
        return "event CS2113 team project meeting /from Sep 1st 6pm /to Sep 1st 8pm";
    }
}
