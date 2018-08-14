package org.uberfire.client.editor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.uberfire.backend.vfs.Path;
import org.uberfire.client.workbench.type.ClientResourceType;
import org.uberfire.workbench.category.Category;
import org.uberfire.workbench.category.Others;

@ApplicationScoped
public class CaioResourceType implements ClientResourceType {

    private final Category category;

    @Inject
    public CaioResourceType(final Others category) {
        this.category = category;
    }

    @Override
    public String getShortName() {
        return "caio";
    }

    @Override
    public String getDescription() {
        return "Caio's file format";
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public String getSuffix() {
        return "caio";
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getSimpleWildcardPattern() {
        return "*.caio";
    }

    @Override
    public boolean accept(Path path) {
        return path.getFileName().endsWith("." + getSuffix());
    }

    @Override
    public Category getCategory() {
        return this.category;
    }
}
