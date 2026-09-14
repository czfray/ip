package kevie.tasks;

import kevie.Kevie;
import kevie.exceptions.TaskIncoRawFormatException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskList {

    private static final int LIST_MAX_LEN = 100;
    public static TaskList instance;

    private Task[] tasks;
    private int length;
    private int maxLength;
    private String save_path;

    public TaskList(int maxLength, String save_path){
        this.tasks = new Task[maxLength];
        this.length = 0;
        this.maxLength = maxLength;
        this.save_path = save_path;
    }

    public void addTask(Task newTask){

        if (newTask == null){
            return;
        }

        if (length >= maxLength){
            System.out.println("Task list has already reached maximum of " + maxLength + " tasks.");
            return;
        }

        tasks[length] = newTask;
        length++;
    }

    //The index here starts at 0 btw
    public Task getTask(int index){

        if (index >= length || index < 0) {
            return null;
        }

        return tasks[index];
    }

    public int getLength() {
        return length;
    }

    public void printAll(){
        for (int i = 0; i < length; i++) {
            Kevie.speak((i + 1) + ". " + tasks[i].toString(), true);
        }
    }

    public void save(){
        try{

            FileWriter writer = new FileWriter(save_path);

            for (int i = 0; i < getLength(); i++) {
                writer.write(getTask(i).getRaw());
                if (i < getLength() - 1) writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            Kevie.speak("I cannot save the list of tasks because an IO error occurred.");
        }
    }

    public static TaskList load(String path){
        TaskList tasks = new TaskList(100, path);
        int lineNo = 0;
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                try{
                    String[] rawArgs = scanner.nextLine().split(Pattern.quote(Task.RAW_SEPERATOR));
                    lineNo++;
                    switch (rawArgs[0]){
                        case "T":
                            tasks.addTask(new Todo(rawArgs));
                            break;
                        case "D":
                            tasks.addTask(new Deadline(rawArgs));
                            break;
                        case "E":
                            tasks.addTask(new Event(rawArgs));
                            break;
                        default:
                            throw new TaskIncoRawFormatException();
                    }
                } catch (TaskIncoRawFormatException e) {
                    Kevie.speak("I cannot load a task because line " + lineNo + " of save file is corrupted.");
                    Kevie.speak("I will delete the corrupted line as a result.", true);
                }

            }
            scanner.close();
            tasks.save();
        } catch (IOException e) {
            Kevie.speak("I cannot load the tasks from save file because there is an IO error.");
        }
        return tasks;
    }
}
