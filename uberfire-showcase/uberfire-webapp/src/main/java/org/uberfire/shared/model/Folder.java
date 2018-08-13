package org.uberfire.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private final String name;

    private final List<String> tasks;

    public Folder(final String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public List<String> tasks() {
        return tasks;
    }

    public void addTask(final String task) {
        this.tasks.add(task);
    }

    public void removeTask(final String task) {
        this.tasks.remove(task);
    }
}
