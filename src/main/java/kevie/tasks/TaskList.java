package kevie.tasks;

import kevie.Kevie;

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

    public TaskList(int maxLength){
        this.tasks = new Task[maxLength];
        this.length = 0;
        this.maxLength = maxLength;
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

    public void save(String path){
        try{

            FileWriter writer = new FileWriter(path);

            for (int i = 0; i < getLength(); i++) {
                writer.write(getTask(i).getRaw());
                if (i < getLength() - 1) writer.write(System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TaskList load(String path){
        TaskList tasks = new TaskList(100);
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] rawArgs = scanner.nextLine().split(Pattern.quote(Task.RAW_SEPERATOR));
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
                        break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            return new TaskList(100);
        }
        return tasks;
    }
}
