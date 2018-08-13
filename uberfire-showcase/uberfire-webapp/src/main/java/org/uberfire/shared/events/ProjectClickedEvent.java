package org.uberfire.shared.events;

public class ProjectClickedEvent {

    private final String name;

    private final boolean selected;

    public ProjectClickedEvent(final String name, final boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String name() {
        return name;
    }

    public boolean selected() {
        return selected;
    }
}
