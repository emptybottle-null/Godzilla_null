/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.springframework.core.io.InputStreamSource;
import org.springframework.lang.Nullable;

public interface Resource
extends InputStreamSource {
    public boolean exists();

    default public boolean isReadable() {
        return this.exists();
    }

    default public boolean isOpen() {
        return false;
    }

    default public boolean isFile() {
        return false;
    }

    public URL getURL() throws IOException;

    public URI getURI() throws IOException;

    public File getFile() throws IOException;

    default public ReadableByteChannel readableChannel() throws IOException {
        return Channels.newChannel(this.getInputStream());
    }

    public long contentLength() throws IOException;

    public long lastModified() throws IOException;

    public Resource createRelative(String var1) throws IOException;

    @Nullable
    public String getFilename();

    public String getDescription();
}

