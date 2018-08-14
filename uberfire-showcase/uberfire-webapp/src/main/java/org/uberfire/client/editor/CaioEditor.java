package org.uberfire.client.editor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.editor.view.CaioEditorPresenter;
import org.uberfire.ext.editor.commons.client.BaseEditor;
import org.uberfire.ext.editor.commons.file.DefaultMetadata;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.shared.editor.caio.CaioEditorService;
import org.uberfire.workbench.model.menu.Menus;

import static org.uberfire.ext.editor.commons.client.menu.MenuItems.COPY;
import static org.uberfire.ext.editor.commons.client.menu.MenuItems.DELETE;
import static org.uberfire.ext.editor.commons.client.menu.MenuItems.RENAME;
import static org.uberfire.ext.editor.commons.client.menu.MenuItems.SAVE;

@Dependent
@WorkbenchEditor(identifier = "CaioEditor", supportedTypes = CaioResourceType.class)
public class CaioEditor extends BaseEditor<String, DefaultMetadata> {

    private final CaioResourceType caioResourceType;
    private final CaioEditorPresenter editorPresenter;
    private final Caller<CaioEditorService> editorServiceCaller;

    @Inject
    public CaioEditor(final CaioResourceType caioResourceType,
                      final CaioEditorPresenter editorPresenter,
                      final Caller<CaioEditorService> editorServiceCaller) {

        super(editorPresenter.getView());

        this.caioResourceType = caioResourceType;
        this.editorPresenter = editorPresenter;
        this.editorServiceCaller = editorServiceCaller;
    }

    @OnStartup
    public void onStartup(final ObservablePath path,
                          final PlaceRequest place) {
        init(path,
             place,
             caioResourceType,
             SAVE);
    }

    @Override
    protected void loadContent() {
        editorServiceCaller
                .call((RemoteCallback<String>) this.editorPresenter::loadContent)
                .load(versionRecordManager.getCurrentPath());
    }

    @Override
    protected void save() {
        final String editorContent = editorPresenter.getContent();
        editorServiceCaller.call(t -> loadContent()).save(versionRecordManager.getCurrentPath(), editorContent, null, null);
    }

    @Override
    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @Override
    @WorkbenchPartTitle
    public String getTitleText() {
        return "CaioEditor [" + versionRecordManager.getCurrentPath().getFileName() + "]";
    }

    @WorkbenchPartView
    public IsWidget getView() {
        return editorPresenter.getView();
    }

    @WorkbenchMenu
    public Menus getMenus() {
        return menus;
    }
}
