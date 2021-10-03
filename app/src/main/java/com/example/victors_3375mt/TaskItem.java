package com.example.victors_3375mt;

public class TaskItem {
    String taskDescription;
    boolean isDone;
    boolean isStarred;

    public TaskItem(String taskDescription, boolean isDone, boolean isStarred) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.isStarred = isStarred;
    }
}
