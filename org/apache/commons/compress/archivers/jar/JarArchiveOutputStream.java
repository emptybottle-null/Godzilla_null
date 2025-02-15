/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class JarArchiveOutputStream
extends ZipArchiveOutputStream {
    private boolean jarMarkerAdded = false;

    public JarArchiveOutputStream(OutputStream out) {
        super(out);
    }

    public JarArchiveOutputStream(OutputStream out, String encoding) {
        super(out);
        this.setEncoding(encoding);
    }

    @Override
    public void putArchiveEntry(ArchiveEntry ze) throws IOException {
        if (!this.jarMarkerAdded) {
            ((ZipArchiveEntry)ze).addAsFirstExtraField(JarMarker.getInstance());
            this.jarMarkerAdded = true;
        }
        super.putArchiveEntry(ze);
    }
}

