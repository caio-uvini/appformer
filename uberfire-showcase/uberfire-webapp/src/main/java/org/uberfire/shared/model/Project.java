package org.uberfire.shared.model;

public class Project {

    private final String name;
    private boolean selected;

    public Project(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean selected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
