/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.buildpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fife.rsta.ac.java.PackageMapNode;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;

public class ClasspathLibraryInfo
extends LibraryInfo {
    private Map<String, ClassFile> classNameToClassFile;

    public ClasspathLibraryInfo(String[] classes) {
        this(Arrays.asList(classes), null);
    }

    public ClasspathLibraryInfo(List<String> classes) {
        this(classes, null);
    }

    public ClasspathLibraryInfo(List<String> classes, SourceLocation sourceLoc) {
        this.setSourceLocation(sourceLoc);
        this.classNameToClassFile = new HashMap<String, ClassFile>();
        int count = classes == null ? 0 : classes.size();
        for (int i = 0; i < count; ++i) {
            String entryName = classes.get(i);
            entryName = entryName.replace('.', '/') + ".class";
            this.classNameToClassFile.put(entryName, null);
        }
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
        int res = -1;
        if (info instanceof ClasspathLibraryInfo) {
            ClasspathLibraryInfo other = (ClasspathLibraryInfo)info;
            res = this.classNameToClassFile.size() - other.classNameToClassFile.size();
            if (res == 0) {
                for (String key : this.classNameToClassFile.keySet()) {
                    if (other.classNameToClassFile.containsKey(key)) continue;
                    res = -1;
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public ClassFile createClassFile(String entryName) throws IOException {
        return this.createClassFileBulk(entryName);
    }

    @Override
    public ClassFile createClassFileBulk(String entryName) throws IOException {
        ClassFile cf = null;
        if (this.classNameToClassFile.containsKey(entryName) && (cf = this.classNameToClassFile.get(entryName)) == null) {
            cf = this.createClassFileImpl(entryName);
            this.classNameToClassFile.put(entryName, cf);
        }
        return cf;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ClassFile createClassFileImpl(String res) throws IOException {
        ClassFile cf = null;
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(res);
        if (in != null) {
            try {
                BufferedInputStream bin = new BufferedInputStream(in);
                DataInputStream din = new DataInputStream(bin);
                cf = new ClassFile(din);
            } finally {
                in.close();
            }
        }
        return cf;
    }

    @Override
    public PackageMapNode createPackageMap() {
        PackageMapNode root = new PackageMapNode();
        for (String className : this.classNameToClassFile.keySet()) {
            root.add(className);
        }
        return root;
    }

    @Override
    public long getLastModified() {
        return 0L;
    }

    @Override
    public String getLocationAsString() {
        return null;
    }

    @Override
    public int hashCode() {
        return this.classNameToClassFile.hashCode();
    }
}

