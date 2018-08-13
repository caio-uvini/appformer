package org.uberfire.client.screens.popup;

import com.google.gwt.core.client.GWT;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NewFolderView implements NewFolderPresenter.View, IsElement {

    private NewFolderPresenter presenter;

    private Modal modal;

    @Inject
    NewFolderContentView contentView;

    @Override
    public void init(final NewFolderPresenter presenter) {
        this.presenter = presenter;
        contentView.init(presenter::newFolder, presenter::close);

        createModal();
    }

    @Override
    public void show() {
        modal.show();
    }

    @Override
    public void hide() {
        modal.hide();
        contentView.clearContent();
    }

    private void createModal() {

        this.modal = GWT.create(Modal.class);

        final ModalBody body = GWT.create(ModalBody.class);
        body.add(ElementWrapperWidget.getWidget(contentView.getElement()));
        modal.add(body);
    }
}
