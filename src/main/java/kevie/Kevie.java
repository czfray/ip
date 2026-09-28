package kevie;

import kevie.commands.*;
import kevie.exceptions.FileCorruptionException;
import kevie.exceptions.KevieException;
import kevie.tasks.TaskList;

import java.util.Scanner;

public class Kevie {
    private UserInterface ui;
    private TaskList taskList;
    private Parser parser;

    public static void main(String[] args) {
        new Kevie().run();
    }

    public Kevie(){
        ui = new UserInterface();
        ui.printBanner();
        ui.botSpeak("Hey, what's up!");

        try{
            this.taskList = TaskList.load(ui);
        } catch (FileCorruptionException e){
            taskList = new TaskList();
            e.printMessage();
        }

        registerCommands();
        ui.botSpeak("Anything you want to get done today?");
    }

    private void registerCommands(){
        parser = new Parser(ui, taskList);
        parser.register(new ByeCommand(ui));
        parser.register(new HelpCommand(ui, parser));
        parser.register(new ListCommand(ui, taskList));
        parser.register(new TodoCommand(ui, taskList));
        parser.register(new DeadlineCommand(ui, taskList));
        parser.register(new EventCommand(ui, taskList));
        parser.register(new MarkCommand(ui, taskList));
        parser.register(new UnmarkCommand(ui, taskList));
        parser.register(new DeleteCommand(ui, taskList));
        parser.register(new FindCommand(ui, taskList));
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            ui.userPrompt();
            String input = scanner.nextLine();
            try{
                if (parser.parseAndExecute(input)){
                    break;
                }
            } catch (KevieException e){
                e.printMessage();
            }
        }
    }
}
