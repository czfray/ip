package kevie.tasks;

import kevie.Kevie;

import java.util.ArrayList;

public class TaskList {

    private static final int LIST_MAX_LEN = 100;
    public static TaskList instance = new TaskList(LIST_MAX_LEN);

    private ArrayList<Task> tasks;

    public TaskList(int maxLength){
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

    public void printAll(){
        for (int i = 0; i < getLength(); i++) {
            Kevie.speak((i + 1) + ". " + tasks.get(i).toString(), true);
        }
    }


}
