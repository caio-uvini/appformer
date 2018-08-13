package org.uberfire.client.screens.popup;

import org.uberfire.client.screens.caio.tasks.ProjectsPresenter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NewProjectPresenter {

    private ProjectsPresenter projectsPresenter;


    public interface View {

        void show();

        void hide();

        void init(NewProjectPresenter presenter);

    }

    @Inject
    private View view;

    @PostConstruct
    public void setuo() {
        view.init(this);
    }

    public void show(final ProjectsPresenter projectsPresenter) {
        this.projectsPresenter = projectsPresenter;
        view.show();
    }

    public void newProject(final String projectName) {
        projectsPresenter.createNewProject(projectName);
        view.hide();
    }

    public void close() {
        view.hide();
    }

}
