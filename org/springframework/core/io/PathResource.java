/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class PathResource
extends AbstractResource
implements WritableResource {
    private final Path path;

    public PathResource(Path path) {
        Assert.notNull((Object)path, "Path must not be null");
        this.path = path.normalize();
    }

    public PathResource(String path) {
        Assert.notNull((Object)path, "Path must not be null");
        this.path = Paths.get(path, new String[0]).normalize();
    }

    public PathResource(URI uri) {
        Assert.notNull((Object)uri, "URI must not be null");
        this.path = Paths.get(uri).normalize();
    }

    public final String getPath() {
        return this.path.toString();
    }

    @Override
    public boolean exists() {
        return Files.exists(this.path, new LinkOption[0]);
    }

    @Override
    public boolean isReadable() {
        return Files.isReadable(this.path) && !Files.isDirectory(this.path, new LinkOption[0]);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!this.exists()) {
            throw new FileNotFoundException(this.getPath() + " (no such file or directory)");
        }
        if (Files.isDirectory(this.path, new LinkOption[0])) {
            throw new FileNotFoundException(this.getPath() + " (is a directory)");
        }
        return Files.newInputStream(this.path, new OpenOption[0]);
    }

    @Override
    public boolean isWritable() {
        return Files.isWritable(this.path) && !Files.isDirectory(this.path, new LinkOption[0]);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (Files.isDirectory(this.path, new LinkOption[0])) {
            throw new FileNotFoundException(this.getPath() + " (is a directory)");
        }
        return Files.newOutputStream(this.path, new OpenOption[0]);
    }

    @Override
    public URL getURL() throws IOException {
        return this.path.toUri().toURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.path.toUri();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public File getFile() throws IOException {
        try {
            return this.path.toFile();
        } catch (UnsupportedOperationException ex) {
            throw new FileNotFoundException(this.path + " cannot be resolved to absolute file path");
        }
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        try {
            return Files.newByteChannel(this.path, StandardOpenOption.READ);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @Override
    public WritableByteChannel writableChannel() throws IOException {
        return Files.newByteChannel(this.path, StandardOpenOption.WRITE);
    }

    @Override
    public long contentLength() throws IOException {
        return Files.size(this.path);
    }

    @Override
    public long lastModified() throws IOException {
        return Files.getLastModifiedTime(this.path, new LinkOption[0]).toMillis();
    }

    @Override
    public Resource createRelative(String relativePath) {
        return new PathResource(this.path.resolve(relativePath));
    }

    @Override
    public String getFilename() {
        return this.path.getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "path [" + this.path.toAbsolutePath() + "]";
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof PathResource && this.path.equals(((PathResource)other).path);
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

