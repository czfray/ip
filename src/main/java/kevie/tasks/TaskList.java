package kevie.tasks;

import kevie.Kevie;
import kevie.exceptions.FileBadRawException;
import kevie.exceptions.FileCorruptionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.ArrayList;

public class TaskList {

    public static TaskList instance;


    private ArrayList<Task> tasks;
    private String save_path;

    public TaskList(String save_path){
        tasks = new ArrayList<Task>();
        this.save_path = save_path;
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

    public void printAll(){
        for (int i = 0; i < getLength(); i++) {
            Kevie.speak((i + 1) + ". " + tasks.get(i).toString(), true);
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

    public static TaskList load(String path) throws FileCorruptionException {
        TaskList tasks = new TaskList(path);
        int lineNo = 0;

        try{
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
                            tasks.addTask(new Todo(rawArgs, lineNo));
                            break;
                        case "D":
                            tasks.addTask(new Deadline(rawArgs, lineNo));
                            break;
                        case "E":
                            tasks.addTask(new Event(rawArgs, lineNo));
                            break;
                        default:
                            throw new FileBadRawException(lineNo);
                    }
                }
                catch (FileBadRawException e){
                    e.printMessage();
                }
            }
            scanner.close();

        } catch (IOException e){
            throw new FileCorruptionException("Cannot open file.");
        }

        tasks.save();
        return tasks;
    }
}
