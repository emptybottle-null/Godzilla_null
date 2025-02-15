/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;

public abstract class AbstractResource
implements Resource {
    @Override
    public boolean exists() {
        Log logger;
        block6: {
            if (this.isFile()) {
                try {
                    return this.getFile().exists();
                } catch (IOException ex) {
                    logger = LogFactory.getLog(this.getClass());
                    if (!logger.isDebugEnabled()) break block6;
                    logger.debug("Could not retrieve File for existence check of " + this.getDescription(), ex);
                }
            }
        }
        try {
            this.getInputStream().close();
            return true;
        } catch (Throwable ex) {
            logger = LogFactory.getLog(this.getClass());
            if (logger.isDebugEnabled()) {
                logger.debug("Could not retrieve InputStream for existence check of " + this.getDescription(), ex);
            }
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        return this.exists();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(this.getDescription() + " cannot be resolved to URL");
    }

    @Override
    public URI getURI() throws IOException {
        URL url = this.getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException ex) {
            throw new NestedIOException("Invalid URI [" + url + "]", ex);
        }
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(this.getDescription() + " cannot be resolved to absolute file path");
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return Channels.newChannel(this.getInputStream());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long contentLength() throws IOException {
        InputStream is = this.getInputStream();
        try {
            int read;
            long size = 0L;
            byte[] buf = new byte[256];
            while ((read = is.read(buf)) != -1) {
                size += (long)read;
            }
            long l = size;
            return l;
        } finally {
            block8: {
                try {
                    is.close();
                } catch (IOException ex) {
                    Log logger = LogFactory.getLog(this.getClass());
                    if (!logger.isDebugEnabled()) break block8;
                    logger.debug("Could not close content-length InputStream for " + this.getDescription(), ex);
                }
            }
        }
    }

    @Override
    public long lastModified() throws IOException {
        File fileToCheck = this.getFileForLastModifiedCheck();
        long lastModified = fileToCheck.lastModified();
        if (lastModified == 0L && !fileToCheck.exists()) {
            throw new FileNotFoundException(this.getDescription() + " cannot be resolved in the file system for checking its last-modified timestamp");
        }
        return lastModified;
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return this.getFile();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        throw new FileNotFoundException("Cannot create a relative resource for " + this.getDescription());
    }

    @Override
    @Nullable
    public String getFilename() {
        return null;
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof Resource && ((Resource)other).getDescription().equals(this.getDescription());
    }

    public int hashCode() {
        return this.getDescription().hashCode();
    }

    public String toString() {
        return this.getDescription();
    }
}

