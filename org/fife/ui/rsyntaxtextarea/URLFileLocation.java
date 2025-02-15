/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.fife.ui.rsyntaxtextarea.FileLocation;

class URLFileLocation
extends FileLocation {
    private URL url;
    private String fileFullPath;
    private String fileName;

    URLFileLocation(URL url) {
        this.url = url;
        this.fileFullPath = this.createFileFullPath();
        this.fileName = this.createFileName();
    }

    private String createFileFullPath() {
        String fullPath = this.url.toString();
        fullPath = fullPath.replaceFirst("://([^:]+)(?:.+)@", "://$1@");
        return fullPath;
    }

    private String createFileName() {
        String fileName = this.url.getPath();
        if (fileName.startsWith("/%2F/")) {
            fileName = fileName.substring(4);
        } else if (fileName.startsWith("/")) {
            fileName = fileName.substring(1);
        }
        return fileName;
    }

    @Override
    protected long getActualLastModified() {
        return 0L;
    }

    @Override
    public String getFileFullPath() {
        return this.fileFullPath;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return this.url.openStream();
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        return this.url.openConnection().getOutputStream();
    }

    @Override
    public boolean isLocal() {
        return "file".equalsIgnoreCase(this.url.getProtocol());
    }

    @Override
    public boolean isLocalAndExists() {
        return false;
    }
}

