package org.uberfire.client.screens.widgets;

import org.jboss.errai.common.client.dom.ListItem;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class ProjectItem implements IsElement {

    @Inject
    @DataField
    ListItem project;

    public void init(final String projectName, boolean active, Command onClick) {
        if (active) {
            project.setClassName("list-group-item active");
        }
        project.setTextContent(projectName);
        project.setOnclick(e -> onClick.execute());
    }

}
