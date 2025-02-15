/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.fife.ui.rsyntaxtextarea.FileLocation;

class FileFileLocation
extends FileLocation {
    private File file;

    FileFileLocation(File file) {
        try {
            this.file = file.getCanonicalFile();
        } catch (IOException ioe) {
            this.file = file;
        }
    }

    @Override
    protected long getActualLastModified() {
        return this.file.lastModified();
    }

    @Override
    public String getFileFullPath() {
        return this.file.getAbsolutePath();
    }

    @Override
    public String getFileName() {
        return this.file.getName();
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }

    @Override
    public boolean isLocal() {
        return true;
    }

    @Override
    public boolean isLocalAndExists() {
        return this.file.exists();
    }
}

