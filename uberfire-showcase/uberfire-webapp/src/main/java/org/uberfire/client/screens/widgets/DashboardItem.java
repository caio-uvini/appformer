package org.uberfire.client.screens.widgets;

import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class DashboardItem implements IsElement {

    @Inject
    @DataField("dashboard-item")
    UnorderedList dashboardItem;

    @Inject
    @DataField("project-name")
    Span projectName;

    @Inject
    @DataField("todo")
    Span todo;

    @Inject
    @DataField("done")
    Span done;

    public void init(final String projectName, final String todo, final String done) {

        this.projectName.setTextContent(projectName.toUpperCase());
        this.todo.setTextContent(todo);
        this.done.setTextContent(done);
    }

}
