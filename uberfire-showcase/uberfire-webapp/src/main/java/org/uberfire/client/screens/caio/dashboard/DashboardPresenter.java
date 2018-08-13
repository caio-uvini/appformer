package org.uberfire.client.screens.caio.dashboard;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberElemental;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.shared.events.TaskCreatedEvent;
import org.uberfire.shared.events.TaskDoneEvent;
import org.uberfire.shared.model.ProjectTasksCounter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@WorkbenchScreen(identifier = "DashboardPresenter")
public class DashboardPresenter {

    public interface View extends UberElemental<DashboardPresenter> {

        void addProject(final String project, final String tasksCreated, final String tasksDone);

        void clear();


    }


    @Inject
    private View view;

    private Map<String, ProjectTasksCounter> projectTasksCounter = new HashMap<>();

    @WorkbenchPartTitle
    public String getTitle() {
        return "Dashboard";
    }

    @WorkbenchPartView
    public UberElemental<DashboardPresenter> getView() {
        return this.view;
    }

    @OnOpen
    public void onOpen() {
        updateView();
    }

    private void updateView() {
        view.clear();
        projectTasksCounter.keySet().forEach(project -> {
            final ProjectTasksCounter projectTasksCounter = this.projectTasksCounter.get(project);
            view.addProject(project, projectTasksCounter.getTasksCreated(), projectTasksCounter.getTasksDone());
        });
    }

    private void taskCreated(@Observes TaskCreatedEvent taskCreatedEvent) {
        final ProjectTasksCounter counter = getProjectTasksCounter(taskCreatedEvent.projectName());
        counter.taskCreated();
    }

    private void taskDone(@Observes TaskDoneEvent taskDoneEvent) {
        final ProjectTasksCounter counter = getProjectTasksCounter(taskDoneEvent.projectName());
        counter.taskDone();
    }

    public ProjectTasksCounter getProjectTasksCounter(final String projectName) {

        ProjectTasksCounter counter = this.projectTasksCounter.get(projectName);
        if (counter == null) {
            counter = new ProjectTasksCounter();
            this.projectTasksCounter.put(projectName, counter);
        }

        return counter;
    }

}
