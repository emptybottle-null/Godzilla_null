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
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class FileSystemResource
extends AbstractResource
implements WritableResource {
    private final String path;
    @Nullable
    private final File file;
    private final Path filePath;

    public FileSystemResource(String path) {
        Assert.notNull((Object)path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.file = new File(path);
        this.filePath = this.file.toPath();
    }

    public FileSystemResource(File file) {
        Assert.notNull((Object)file, "File must not be null");
        this.path = StringUtils.cleanPath(file.getPath());
        this.file = file;
        this.filePath = file.toPath();
    }

    public FileSystemResource(Path filePath) {
        Assert.notNull((Object)filePath, "Path must not be null");
        this.path = StringUtils.cleanPath(filePath.toString());
        this.file = null;
        this.filePath = filePath;
    }

    public FileSystemResource(FileSystem fileSystem, String path) {
        Assert.notNull((Object)fileSystem, "FileSystem must not be null");
        Assert.notNull((Object)path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.file = null;
        this.filePath = fileSystem.getPath(this.path, new String[0]).normalize();
    }

    public final String getPath() {
        return this.path;
    }

    @Override
    public boolean exists() {
        return this.file != null ? this.file.exists() : Files.exists(this.filePath, new LinkOption[0]);
    }

    @Override
    public boolean isReadable() {
        return this.file != null ? this.file.canRead() && !this.file.isDirectory() : Files.isReadable(this.filePath) && !Files.isDirectory(this.filePath, new LinkOption[0]);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        try {
            return Files.newInputStream(this.filePath, new OpenOption[0]);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @Override
    public boolean isWritable() {
        return this.file != null ? this.file.canWrite() && !this.file.isDirectory() : Files.isWritable(this.filePath) && !Files.isDirectory(this.filePath, new LinkOption[0]);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return Files.newOutputStream(this.filePath, new OpenOption[0]);
    }

    @Override
    public URL getURL() throws IOException {
        return this.file != null ? this.file.toURI().toURL() : this.filePath.toUri().toURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.file != null ? this.file.toURI() : this.filePath.toUri();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public File getFile() {
        return this.file != null ? this.file : this.filePath.toFile();
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        try {
            return FileChannel.open(this.filePath, StandardOpenOption.READ);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @Override
    public WritableByteChannel writableChannel() throws IOException {
        return FileChannel.open(this.filePath, StandardOpenOption.WRITE);
    }

    @Override
    public long contentLength() throws IOException {
        if (this.file != null) {
            long length = this.file.length();
            if (length == 0L && !this.file.exists()) {
                throw new FileNotFoundException(this.getDescription() + " cannot be resolved in the file system for checking its content length");
            }
            return length;
        }
        try {
            return Files.size(this.filePath);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @Override
    public long lastModified() throws IOException {
        if (this.file != null) {
            return super.lastModified();
        }
        try {
            return Files.getLastModifiedTime(this.filePath, new LinkOption[0]).toMillis();
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @Override
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
        return this.file != null ? new FileSystemResource(pathToUse) : new FileSystemResource(this.filePath.getFileSystem(), pathToUse);
    }

    @Override
    public String getFilename() {
        return this.file != null ? this.file.getName() : this.filePath.getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "file [" + (this.file != null ? this.file.getAbsolutePath() : this.filePath.toAbsolutePath()) + "]";
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof FileSystemResource && this.path.equals(((FileSystemResource)other).path);
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

