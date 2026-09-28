package kevie.tasks;

import kevie.Kevie;
import kevie.UserInterface;
import kevie.exceptions.FileBadRawException;
import kevie.exceptions.FileCorruptionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.ArrayList;

public class TaskList {
    private static String SAVE_PATH = "saves/tasks.kv";

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task newTask){

        if (newTask == null){
            return;
        }
        tasks.add(newTask);
    }

    public void deleteTask(int index){

        if (index >= tasks.size() || index < 0) {
            return;
        }
        tasks.remove(index);
    }

    //The index here starts at 0 btw
    public Task getTask(int index){

        if (index >= tasks.size() || index < 0) {
            return null;
        }
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public void printAll(UserInterface ui){
        for (int i = 0; i < getLength(); i++) {
            ui.botSpeak((i + 1) + ". " + tasks.get(i).toString(), true);
        }
    }

    public ArrayList<Integer> keywordFindTasks(String keyword){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < getLength(); i++){
            if (tasks.get(i).getName().toLowerCase().contains(keyword.toLowerCase())){
                result.add(i);
            }
        }
        return result;
    }

    public void save(UserInterface ui){
        try{

            FileWriter writer = new FileWriter(SAVE_PATH);

            for (int i = 0; i < getLength(); i++) {
                writer.write(getTask(i).getRaw());
                if (i < getLength() - 1) writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            ui.botSpeak("I cannot save the list of tasks because an IO error occurred.");
        }
    }

    public static TaskList load(UserInterface ui) throws FileCorruptionException {
        TaskList tasks = new TaskList();
        int lineNo = 0;

        try{
            File file = new File(SAVE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                try{
                    String[] rawArgs = scanner.nextLine().split(Pattern.quote(Task.RAW_SEPERATOR));
                    lineNo++;
                    switch (rawArgs[0]){
                        case "T":
                            tasks.addTask(new Todo(rawArgs, lineNo, ui));
                            break;
                        case "D":
                            tasks.addTask(new Deadline(rawArgs, lineNo, ui));
                            break;
                        case "E":
                            tasks.addTask(new Event(rawArgs, lineNo, ui));
                            break;
                        default:
                            throw new FileBadRawException(lineNo, ui);
                    }
                }
                catch (FileBadRawException e){
                    e.printMessage();
                }
            }
            scanner.close();

        } catch (IOException e){
            throw new FileCorruptionException("Cannot open file.", ui);
        }

        tasks.save(ui);
        return tasks;
    }
}
