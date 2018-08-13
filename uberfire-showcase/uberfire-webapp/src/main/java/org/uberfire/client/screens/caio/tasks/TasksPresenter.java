package org.uberfire.client.screens.caio.tasks;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberElemental;
import org.uberfire.client.screens.popup.NewFolderPresenter;
import org.uberfire.shared.events.ProjectClickedEvent;
import org.uberfire.shared.events.TaskCreatedEvent;
import org.uberfire.shared.events.TaskDoneEvent;
import org.uberfire.shared.model.Folder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
@WorkbenchScreen(identifier = "TasksPresenter")
public class TasksPresenter {

    public interface View extends UberElemental<TasksPresenter> {

        void setCurrentProject(final String projectName);

        void setCanCreateNewFolder(final boolean canCreateNewFolder);

        void clearTasks();

        void newFolder(final String name, final Integer size, final List<String> tasks);

    }


    @Inject
    private View view;

    @Inject
    private NewFolderPresenter newFolderPresenter;

    @Inject
    private Event<TaskCreatedEvent> taskCreatedEvent;

    @Inject
    private Event<TaskDoneEvent> taskDoneEvent;

    private String currentSelectedProject;

    private Map<String, List<Folder>> folderByProject = new HashMap<>();

    @WorkbenchPartTitle
    public String getTitle() {
        return "Tasks";
    }

    @WorkbenchPartView
    public View getView() {
        return this.view;
    }

    @PostConstruct
    public void setup() {
        this.view.init(this);
    }

    public void projectSelected(@Observes ProjectClickedEvent projectClickedEvent) {
        this.currentSelectedProject = projectClickedEvent.selected() ? projectClickedEvent.name() : null;
        view.setCanCreateNewFolder(projectClickedEvent.selected());
        updateView();
    }

    public void showNewFolder() {
        newFolderPresenter.show(this);
    }

    public void createTask(final String folderName, final String task) {

        final Optional<Folder> folderOpt = findFolder(folderName);
        folderOpt.ifPresent(folder -> folder.addTask(task));

        taskCreatedEvent.fire(new TaskCreatedEvent(currentSelectedProject, folderName, task));
        updateView();
    }

    private Optional<Folder> findFolder(final String folderName) {
        return getFolders().stream().filter(folder -> folder.name().equals(folderName)).findFirst();
    }

    public void doneTask(final String folderName, final String task) {

        final Optional<Folder> folderOpt = findFolder(folderName);
        folderOpt.ifPresent(folder -> folder.removeTask(task));

        taskDoneEvent.fire(new TaskDoneEvent(currentSelectedProject, folderName, task));
        updateView();
    }

    private List<Folder> getFolders() {

        if (currentSelectedProject == null) {
            return new ArrayList<>();
        }

        final List<Folder> folders = folderByProject.get(currentSelectedProject);
        if (folders == null) {
            return new ArrayList<>();
        }

        return folders;
    }

    private void updateView() {

        this.view.setCurrentProject(currentSelectedProject);
        view.clearTasks();

        getFolders().forEach(folder -> view.newFolder(folder.name(), folder.tasks().size(), folder.tasks()));
    }

    public void newFolder(final String folderName) {

        if (currentSelectedProject == null) {
            return;
        }

        final List<Folder> folders = getFolders();
        folders.add(new Folder(folderName));
        folderByProject.put(currentSelectedProject, folders);
        updateView();
    }

}
