package org.uberfire.client.screens.caio.dashboard;

import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.screens.widgets.DashboardItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.errai.common.client.dom.DOMUtil.removeAllChildren;

@Dependent
@Templated
public class DashboardView implements DashboardPresenter.View, IsElement {

    private DashboardPresenter presenter;

    @Inject
    @DataField("projects")
    Div projects;

    @Inject
    ManagedInstance<DashboardItem> dashboardItems;


    @Override
    public void init(final DashboardPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addProject(final String project, final String tasksCreated, final String tasksDone) {
        final DashboardItem dashboardItem = dashboardItems.get();
        dashboardItem.init(project, tasksCreated, tasksDone);
        projects.appendChild(dashboardItem.getElement());
    }

    @Override
    public void clear() {
        removeAllChildren(projects);
    }
}
