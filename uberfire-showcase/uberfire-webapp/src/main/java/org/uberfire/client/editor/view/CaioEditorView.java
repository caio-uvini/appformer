package org.uberfire.client.editor.view;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLTextAreaElement;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.ext.editor.commons.client.EditorTitle;

@Dependent
@Templated
public class CaioEditorView implements CaioEditorPresenter.View,
                                       IsElement {

    @Inject
    @DataField("current-content")
    private HTMLTextAreaElement currentContentArea;

    @Inject
    @DataField("new-content")
    private HTMLTextAreaElement inputContentArea;

    private CaioEditorPresenter presenter;
    private EditorTitle editorTitle;

    @Override
    public void init(final CaioEditorPresenter presenter) {
        this.presenter = presenter;
        this.editorTitle = new EditorTitle();
    }

    @Override
    public void showFileContent(final String content) {
        currentContentArea.textContent = content;
        inputContentArea.value = content;
    }

    @Override
    public String getContent() {
        return inputContentArea.value;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showSaving() {

    }

    @Override
    public void alertReadOnly() {

    }

    @Override
    public EditorTitle getTitleWidget() {
        return this.editorTitle;
    }

    @Override
    public void refreshTitle(String value) {
        this.editorTitle.setText(value);
    }

    @Override
    public boolean confirmClose() {
        return false;
    }

    @Override
    public void showBusyIndicator(String message) {

    }

    @Override
    public void hideBusyIndicator() {

    }

    @Override
    public Widget asWidget() {
        return ElementWrapperWidget.getWidget(getElement());
    }
}
