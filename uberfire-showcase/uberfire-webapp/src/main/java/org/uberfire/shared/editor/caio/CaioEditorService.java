package org.uberfire.shared.editor.caio;

import org.jboss.errai.bus.server.annotations.Remote;
import org.uberfire.ext.editor.commons.file.DefaultMetadata;
import org.uberfire.ext.editor.commons.service.support.SupportsRead;
import org.uberfire.ext.editor.commons.service.support.SupportsUpdate;

@Remote
public interface CaioEditorService extends SupportsRead<String>,
                                           SupportsUpdate<String, DefaultMetadata> {

}
