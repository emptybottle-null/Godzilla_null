/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.fife.ui.rsyntaxtextarea.FileFileLocation;
import org.fife.ui.rsyntaxtextarea.URLFileLocation;

public abstract class FileLocation {
    public static FileLocation create(String fileFullPath) {
        if (fileFullPath.startsWith("http://") || fileFullPath.startsWith("https://") || fileFullPath.startsWith("ftp://")) {
            try {
                return new URLFileLocation(new URL(fileFullPath));
            } catch (MalformedURLException mue) {
                throw new IllegalArgumentException("Not a valid URL: " + fileFullPath, mue);
            }
        }
        return new FileFileLocation(new File(fileFullPath));
    }

    public static FileLocation create(File file) {
        return new FileFileLocation(file);
    }

    public static FileLocation create(URL url) {
        if ("file".equalsIgnoreCase(url.getProtocol())) {
            return new FileFileLocation(new File(url.getPath()));
        }
        return new URLFileLocation(url);
    }

    protected abstract long getActualLastModified();

    public abstract String getFileFullPath();

    public abstract String getFileName();

    protected abstract InputStream getInputStream() throws IOException;

    protected abstract OutputStream getOutputStream() throws IOException;

    public abstract boolean isLocal();

    public abstract boolean isLocalAndExists();

    public boolean isRemote() {
        return !this.isLocal();
    }
}

