package org.uberfire.client.screens.caio.tasks;

import com.google.gwt.user.client.Event;
import elemental2.dom.HTMLLabelElement;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.DOMUtil;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.Document;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.screens.widgets.FolderItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
@Templated("tasks-view.html")
public class TasksView implements TasksPresenter.View, IsElement {

    @Inject
    @DataField("current-project")
    private HTMLLabelElement currentProjectLabel;

    @Inject
    Document document;

    @Inject
    @DataField("tasks")
    Div tasks;

    @Inject
    @DataField("new-folder")
    private Button newFolderButton;

    @Inject
    ManagedInstance<FolderItem> folders;

    private TasksPresenter tasksPresenter;

    @Override
    public void init(final TasksPresenter presenter) {
        this.tasksPresenter = presenter;
        this.newFolderButton.setDisabled(true);
    }

    @Override
    public void setCurrentProject(final String projectName) {
        currentProjectLabel.textContent = projectName != null ? projectName : "";
    }

    @Override
    public void setCanCreateNewFolder(final boolean canCreateNewFolder) {
        this.newFolderButton.setDisabled(!canCreateNewFolder);
    }

    @Override
    public void clearTasks() {
        DOMUtil.removeAllChildren(tasks);
    }

    @Override
    public void newFolder(String name, Integer size, List<String> tasksList) {

        final FolderItem folderItem = createFolder(name, String.valueOf(size));
        createTasks(name, tasksList, folderItem);

        tasks.appendChild(folderItem.getElement());
    }

    private void createTasks(final String folderName, final List<String> tasksList, final FolderItem folderItem) {
        tasksList.forEach(task -> folderItem.createTask(task, () -> tasksPresenter.doneTask(folderName, task)));
    }

    private FolderItem createFolder(final String folderName, String numberOfTasks) {

        final FolderItem folder = folders.get();
        folder.init(folderName, numberOfTasks, newTask -> tasksPresenter.createTask(folderName, newTask));

        return folder;
    }

    @SinkNative(Event.ONCLICK)
    @EventHandler("new-folder")
    public void newFolderClick(final Event event) {
        tasksPresenter.showNewFolder();
    }
}
