package org.uberfire.backend.service;

import javax.inject.Inject;

import org.uberfire.backend.server.VFSServicesServerImpl;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.VFSService;
import org.uberfire.ext.editor.commons.file.DefaultMetadata;
import org.uberfire.shared.editor.caio.CaioEditorService;

public class CaioEditorServiceImpl implements CaioEditorService {

    private final VFSService vfsService;

    @Inject
    public CaioEditorServiceImpl(final VFSServicesServerImpl vfsService) {
        this.vfsService = vfsService;
    }

    @Override
    public String load(final Path path) {
        return vfsService.readAllString(path);
    }

    @Override
    public Path save(final Path path, final String content, final DefaultMetadata metadata, final String comment) {
        // override file
        vfsService.delete(path);
        return vfsService.write(path, content);
    }
}
