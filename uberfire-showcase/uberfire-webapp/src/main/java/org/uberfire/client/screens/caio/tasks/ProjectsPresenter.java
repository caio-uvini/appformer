package org.uberfire.client.screens.caio.tasks;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberElemental;
import org.uberfire.client.screens.popup.NewProjectPresenter;
import org.uberfire.shared.events.ProjectClickedEvent;
import org.uberfire.shared.model.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@WorkbenchScreen(identifier = "ProjectsPresenter")
public class ProjectsPresenter {

    public interface View extends UberElemental<ProjectsPresenter> {

        void clearProjects();

        void addProject(String projectName, boolean selected);

    }


    @Inject
    private NewProjectPresenter newProjectPresenter;

    @Inject
    private View view;

    @Inject
    private Event<ProjectClickedEvent> projectSelectedEventEvent;

    @WorkbenchPartTitle
    public String getTitle() {
        return "Projects";
    }

    @WorkbenchPartView
    public UberElemental<ProjectsPresenter> getView() {
        return view;
    }

    private List<Project> projects = new ArrayList<>();

    public void newProject() {
        newProjectPresenter.show(this);
    }

    public void createNewProject(final String projectName) {
        final Project newProject = new Project(projectName);
        projects.add(newProject);

        updateView();
    }

    private void selectProject(final Project project) {
        setActiveProject(project);
        updateView();

        projectSelectedEventEvent.fire(new ProjectClickedEvent(project.name(), project.selected()));
    }

    public void selectProject(final String projectName) {
        findProjectByName(projectName).ifPresent(this::selectProject);
    }

    private void setActiveProject(final Project project) {

        if (project.selected()) {
            project.setSelected(false);
            return;
        }

        deselectAllProjects();
        project.setSelected(true);
    }

    private void deselectAllProjects() {
        projects.forEach(project -> project.setSelected(false));
    }

    private void updateView() {
        view.clearProjects();
        projects.forEach(project -> view.addProject(project.name(), project.selected()));
    }

    private Optional<Project> findProjectByName(final String projectName) {
        return projects.stream().filter(project -> project.name().equals(projectName)).findFirst();
    }

}
