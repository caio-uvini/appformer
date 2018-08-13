package org.uberfire.client.screens.widgets;

import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.Form;
import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class FolderItem implements IsElement {

    @Inject
    @DataField
    UnorderedList folder;

    @Inject
    @DataField
    Span folderName;

    @Inject
    @DataField
    Span numberOfTasks;

    @Inject
    @DataField
    Div tasksList;

    @Inject
    @DataField
    Form newTaskForm;

    @Inject
    @DataField
    Input taskName;

    @Inject
    ManagedInstance<TaskItem> tasks;

    public void init(final String folderName,
                     final String numberOfTasks,
                     final ParameterizedCommand<String> newTaskCommand) {

        this.folderName.setTextContent(folderName);
        this.numberOfTasks.setTextContent(numberOfTasks);

        this.newTaskForm.setOnsubmit(e -> {
            e.preventDefault();
            newTaskCommand.execute(taskName.getValue());
        });
    }

    public void createTask(final String taskTitle, final Command doneCommand) {

        final TaskItem taskItem = tasks.get();
        taskItem.init(taskTitle, doneCommand);

        tasksList.appendChild(taskItem.getElement());
    }

}
