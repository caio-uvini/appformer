package org.uberfire.client.perspectives;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.MultiListWorkbenchPanelPresenter;
import org.uberfire.client.workbench.panels.impl.SimpleWorkbenchPanelPresenter;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.PanelDefinition;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WorkbenchPerspective(identifier = "TasksPerspective")
public class TasksPerspective {

    @Perspective
    public PerspectiveDefinition buildPerspective() {

        final PerspectiveDefinition perspectiveDef =
            new PerspectiveDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
        perspectiveDef.setName("TasksPerspective");

        final PanelDefinition westPanel = new PanelDefinitionImpl(SimpleWorkbenchPanelPresenter.class.getName());
        westPanel.addPart(new PartDefinitionImpl(new DefaultPlaceRequest("ProjectsPresenter")));
        westPanel.setWidth(350);
        perspectiveDef.getRoot().insertChild(CompassPosition.WEST, westPanel);

        perspectiveDef.getRoot().addPart(new PartDefinitionImpl(new DefaultPlaceRequest("TasksPresenter")));

        return perspectiveDef;

    }
}
