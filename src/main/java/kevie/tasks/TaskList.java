package kevie.tasks;

import kevie.Kevie;

public class TaskList {

    private static final int LIST_MAX_LEN = 100;
    public static TaskList instance = new TaskList(LIST_MAX_LEN);

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


}
