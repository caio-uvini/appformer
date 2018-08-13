package org.uberfire.client.screens.popup;

import com.google.gwt.core.client.GWT;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NewProjectView implements NewProjectPresenter.View {

    private NewProjectPresenter newProjectPresenter;

    private Modal modal;

    @Inject
    NewProjectContentView contentView;

    @Override
    public void init(NewProjectPresenter presenter) {
        this.newProjectPresenter = presenter;

        contentView.init(presenter::newProject, presenter::close);
        createModal();
    }

    @Override
    public void show() {
        modal.show();
    }

    @Override
    public void hide() {
        contentView.clearContent();
        modal.hide();
    }

    private void createModal() {
        this.modal = GWT.create(Modal.class);

        final ModalBody body = GWT.create(ModalBody.class);
        body.add(ElementWrapperWidget.getWidget(contentView.getElement()));
        modal.add(body);

    }
}
