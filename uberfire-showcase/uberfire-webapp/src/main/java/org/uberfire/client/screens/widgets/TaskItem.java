package org.uberfire.client.screens.widgets;

import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.common.client.dom.ListItem;
import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class TaskItem implements IsElement {

    @Inject
    @DataField
    ListItem task;

    @Inject
    @DataField
    Span taskName;

    @Inject
    @DataField
    Input done;

    public void init(final String taskTitle, final Command doneCommand) {
        taskName.setTextContent(taskTitle);
        done.setOnclick(e -> doneCommand.execute());
    }
}
