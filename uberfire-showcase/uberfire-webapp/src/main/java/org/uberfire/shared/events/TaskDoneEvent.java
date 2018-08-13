package org.uberfire.shared.events;

public class TaskDoneEvent {

    private final String projectName;
    private final String folder;
    private final String task;

    public TaskDoneEvent(final String projectName, final String folder, final String task) {
        this.projectName = projectName;
        this.folder = folder;
        this.task = task;
    }

    public String projectName() {
        return projectName;
    }

    public String folder() {
        return folder;
    }

    public String task() {
        return task;
    }
}
