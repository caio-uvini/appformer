package org.uberfire.client.perspectives;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.MultiListWorkbenchPanelPresenter;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WorkbenchPerspective(identifier = "DashboardPerspective")
public class DashboardPerspective {

    @Perspective
    public PerspectiveDefinition buildPerspective() {

        final PerspectiveDefinition perspectiveDefinition =
            new PerspectiveDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());

        perspectiveDefinition.setName("DashboardPerspective");
        perspectiveDefinition.getRoot().addPart(new PartDefinitionImpl(new DefaultPlaceRequest("DashboardPresenter")));

        return perspectiveDefinition;
    }

}
