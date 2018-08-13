package org.uberfire.client.screens.popup;

import com.google.gwt.user.client.Event;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class NewFolderContentView implements IsElement {

    @Inject
    @DataField("folder-name")
    Input folderNameTextBox;

    @Inject
    @DataField("ok-button")
    Button okButton;

    @Inject
    @DataField("cancel-button")
    Button cancelButton;

    private ParameterizedCommand<String> addFolderCommand;
    private Command cancelCommand;

    public void init(final ParameterizedCommand<String> addFolderCommand, final Command cancelCommand) {
        this.addFolderCommand = addFolderCommand;
        this.cancelCommand = cancelCommand;
    }

    public void clearContent() {
        this.folderNameTextBox.setValue("");
    }

    @SinkNative(Event.ONCLICK)
    @EventHandler("ok-button")
    public void addFolder(final Event event) {
        addFolderCommand.execute(folderNameTextBox.getValue());
    }

    @SinkNative(Event.ONCLICK)
    @EventHandler("cancel-button")
    public void cancel(final Event event) {
        cancelCommand.execute();
    }
}
