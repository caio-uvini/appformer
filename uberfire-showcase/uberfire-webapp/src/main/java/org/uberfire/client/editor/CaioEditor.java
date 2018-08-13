package org.uberfire.client.editor;

import javax.enterprise.context.Dependent;

import org.uberfire.client.annotations.WorkbenchEditor;
import org.uberfire.ext.editor.commons.client.BaseEditor;

@Dependent
@WorkbenchEditor(identifier = "Caio Editor", supportedTypes = {CaioResourceType.class}, priority = Integer.MAX_VALUE)
public class CaioEditorPresenter extends BaseEditor<String, String> {

    @Override
    protected void loadContent() {

    }
}
