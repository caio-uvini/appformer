package org.uberfire.shared.model;

public class ProjectTasksCounter {

    private Integer tasksCreated;

    private Integer tasksDone;

    public ProjectTasksCounter() {
        this.tasksCreated = 0;
        this.tasksDone = 0;
    }

    public String getTasksCreated() {
        return String.valueOf(tasksCreated);
    }

    public String getTasksDone() {
        return String.valueOf(tasksDone);
    }

    public void taskCreated() {
        tasksCreated++;
    }

    public void taskDone() {
        tasksDone++;
        tasksCreated--;
    }
}
