package org.uberfire.client.screens.caio.tasks;

import com.google.gwt.user.client.Event;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.DOMUtil;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.screens.widgets.ProjectItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated("projects-view.html")
public class ProjectView implements ProjectsPresenter.View, IsElement {

    @Inject
    @DataField("new-project")
    Button newProject;

    @Inject
    @DataField("projects-list")
    UnorderedList projectsList;

    @Inject
    ManagedInstance<ProjectItem> projects;

    private ProjectsPresenter presenter;

    @Override
    public void init(final ProjectsPresenter presenter) {
        this.presenter = presenter;
    }

    @SinkNative(Event.ONCLICK)
    @EventHandler("new-project")
    public void newProject(final Event event) {
        presenter.newProject();
    }

    @Override
    public void clearProjects() {
        DOMUtil.removeAllChildren(projectsList);
    }

    @Override
    public void addProject(String projectName, boolean selected) {

        final ProjectItem item = projects.get();
        item.init(projectName, selected, () -> presenter.selectProject(projectName));
        projectsList.appendChild(item.getElement());
    }
}
