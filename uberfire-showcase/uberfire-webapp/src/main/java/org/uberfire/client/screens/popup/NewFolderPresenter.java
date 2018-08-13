package org.uberfire.client.screens.popup;

import org.uberfire.client.mvp.UberElemental;
import org.uberfire.client.screens.caio.tasks.TasksPresenter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NewFolderPresenter {

    public interface View extends UberElemental<NewFolderPresenter> {

        void show();

        void hide();

        void init(final NewFolderPresenter presenter);

    }

    @Inject
    private View view;

    private TasksPresenter tasksPresenter;

    @PostConstruct
    public void setup() {
        view.init(this);
    }

    public void show(final TasksPresenter tasksPresenter) {
        this.tasksPresenter = tasksPresenter;
        view.show();
    }

    public void newFolder(final String folderName) {
        tasksPresenter.newFolder(folderName);
        view.hide();
    }

    public void close() {
        view.hide();
    }

}
