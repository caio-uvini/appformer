package org.uberfire.client.editor.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.uberfire.client.mvp.UberElemental;
import org.uberfire.ext.editor.commons.client.BaseEditorView;

@Dependent
public class CaioEditorPresenter {

    private final View view;

    @Inject
    public CaioEditorPresenter(final View view) {
        this.view = view;
    }

    @PostConstruct
    public void setup() {
        view.init(this);
    }

    public View getView() {
        return this.view;
    }

    public void loadContent(final String content) {
        this.view.showFileContent(content);
    }

    public String getContent() {
        return this.view.getContent();
    }

    public interface View extends UberElemental<CaioEditorPresenter>,
                                  BaseEditorView {

        void showFileContent(final String content);

        String getContent();
    }
}
