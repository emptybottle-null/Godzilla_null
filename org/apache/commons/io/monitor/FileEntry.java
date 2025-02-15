/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.monitor;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import org.apache.commons.io.FileUtils;

public class FileEntry
implements Serializable {
    private static final long serialVersionUID = -2505664948818681153L;
    static final FileEntry[] EMPTY_FILE_ENTRY_ARRAY = new FileEntry[0];
    private final FileEntry parent;
    private FileEntry[] children;
    private final File file;
    private String name;
    private boolean exists;
    private boolean directory;
    private long lastModified;
    private long length;

    public FileEntry(File file) {
        this(null, file);
    }

    public FileEntry(FileEntry parent, File file) {
        if (file == null) {
            throw new IllegalArgumentException("File is missing");
        }
        this.file = file;
        this.parent = parent;
        this.name = file.getName();
    }

    public boolean refresh(File file) {
        boolean origExists = this.exists;
        long origLastModified = this.lastModified;
        boolean origDirectory = this.directory;
        long origLength = this.length;
        this.name = file.getName();
        this.exists = Files.exists(file.toPath(), new LinkOption[0]);
        this.directory = this.exists && file.isDirectory();
        try {
            this.lastModified = this.exists ? FileUtils.lastModified(file) : 0L;
        } catch (IOException e) {
            this.lastModified = 0L;
        }
        this.length = this.exists && !this.directory ? file.length() : 0L;
        return this.exists != origExists || this.lastModified != origLastModified || this.directory != origDirectory || this.length != origLength;
    }

    public FileEntry newChildInstance(File file) {
        return new FileEntry(this, file);
    }

    public FileEntry getParent() {
        return this.parent;
    }

    public int getLevel() {
        return this.parent == null ? 0 : this.parent.getLevel() + 1;
    }

    public FileEntry[] getChildren() {
        return this.children != null ? this.children : EMPTY_FILE_ENTRY_ARRAY;
    }

    public void setChildren(FileEntry ... children) {
        this.children = children;
    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isExists() {
        return this.exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }
}

