/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.buildpath;

import java.io.File;
import java.io.IOException;
import org.fife.rsta.ac.java.PackageMapNode;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;

public class DirLibraryInfo
extends LibraryInfo {
    private File dir;

    public DirLibraryInfo(File dir) {
        this(dir, null);
    }

    public DirLibraryInfo(String dir) {
        this(new File(dir));
    }

    public DirLibraryInfo(File dir, SourceLocation sourceLoc) {
        this.setDirectory(dir);
        this.setSourceLocation(sourceLoc);
    }

    public DirLibraryInfo(String dir, SourceLocation sourceLoc) {
        this(new File(dir), sourceLoc);
    }

    @Override
    public void bulkClassFileCreationEnd() {
    }

    @Override
    public void bulkClassFileCreationStart() {
    }

    @Override
    public int compareTo(LibraryInfo info) {
        if (info == this) {
            return 0;
        }
        int result = -1;
        if (info instanceof DirLibraryInfo) {
            return this.dir.compareTo(((DirLibraryInfo)info).dir);
        }
        return result;
    }

    @Override
    public ClassFile createClassFile(String entryName) throws IOException {
        return this.createClassFileBulk(entryName);
    }

    @Override
    public ClassFile createClassFileBulk(String entryName) throws IOException {
        File file = new File(this.dir, entryName);
        if (!file.isFile()) {
            System.err.println("ERROR: Invalid class file: " + file.getAbsolutePath());
            return null;
        }
        return new ClassFile(file);
    }

    @Override
    public PackageMapNode createPackageMap() {
        PackageMapNode root = new PackageMapNode();
        this.getPackageMapImpl(this.dir, null, root);
        return root;
    }

    @Override
    public long getLastModified() {
        return this.dir.lastModified();
    }

    @Override
    public String getLocationAsString() {
        return this.dir.getAbsolutePath();
    }

    private void getPackageMapImpl(File dir, String pkg, PackageMapNode root) {
        File[] children;
        for (File child : children = dir.listFiles()) {
            if (child.isFile() && child.getName().endsWith(".class")) {
                if (pkg != null) {
                    root.add(pkg + "/" + child.getName());
                    continue;
                }
                root.add(child.getName());
                continue;
            }
            if (!child.isDirectory()) continue;
            String subpkg = pkg == null ? child.getName() : pkg + "/" + child.getName();
            this.getPackageMapImpl(child, subpkg, root);
        }
    }

    @Override
    public int hashCode() {
        return this.dir.hashCode();
    }

    private void setDirectory(File dir) {
        if (dir == null || !dir.isDirectory()) {
            String name = dir == null ? "null" : dir.getAbsolutePath();
            throw new IllegalArgumentException("Directory does not exist: " + name);
        }
        this.dir = dir;
    }

    public String toString() {
        return "[DirLibraryInfo: jar=" + this.dir.getAbsolutePath() + "; source=" + this.getSourceLocation() + "]";
    }
}

