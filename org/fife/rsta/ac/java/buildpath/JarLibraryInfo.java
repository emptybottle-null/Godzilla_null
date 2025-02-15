/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.buildpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.fife.rsta.ac.java.PackageMapNode;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;

public class JarLibraryInfo
extends LibraryInfo {
    private File jarFile;
    private JarFile bulkCreateJar;

    public JarLibraryInfo(String jarFile) {
        this(new File(jarFile));
    }

    public JarLibraryInfo(File jarFile) {
        this(jarFile, null);
    }

    public JarLibraryInfo(File jarFile, SourceLocation sourceLoc) {
        this.setJarFile(jarFile);
        this.setSourceLocation(sourceLoc);
    }

    @Override
    public void bulkClassFileCreationEnd() {
        try {
            this.bulkCreateJar.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void bulkClassFileCreationStart() {
        try {
            this.bulkCreateJar = new JarFile(this.jarFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public int compareTo(LibraryInfo info) {
        if (info == this) {
            return 0;
        }
        int result = -1;
        if (info instanceof JarLibraryInfo) {
            result = this.jarFile.compareTo(((JarLibraryInfo)info).jarFile);
        }
        return result;
    }

    @Override
    public ClassFile createClassFile(String entryName) throws IOException {
        try (JarFile jar = new JarFile(this.jarFile);){
            ClassFile classFile = JarLibraryInfo.createClassFileImpl(jar, entryName);
            return classFile;
        }
    }

    @Override
    public ClassFile createClassFileBulk(String entryName) throws IOException {
        return JarLibraryInfo.createClassFileImpl(this.bulkCreateJar, entryName);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static ClassFile createClassFileImpl(JarFile jar, String entryName) throws IOException {
        ClassFile cf;
        JarEntry entry = (JarEntry)jar.getEntry(entryName);
        if (entry == null) {
            System.err.println("ERROR: Invalid entry: " + entryName);
            return null;
        }
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(jar.getInputStream(entry)));){
            cf = new ClassFile(in);
        }
        return cf;
    }

    @Override
    public PackageMapNode createPackageMap() throws IOException {
        PackageMapNode root = new PackageMapNode();
        try (JarFile jar = new JarFile(this.jarFile);){
            Enumeration<JarEntry> e = jar.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();
                String entryName = entry.getName();
                if (!entryName.endsWith(".class")) continue;
                root.add(entryName);
            }
        }
        return root;
    }

    @Override
    public long getLastModified() {
        return this.jarFile.lastModified();
    }

    @Override
    public String getLocationAsString() {
        return this.jarFile.getAbsolutePath();
    }

    public File getJarFile() {
        return this.jarFile;
    }

    @Override
    public int hashCode() {
        return this.jarFile.hashCode();
    }

    private void setJarFile(File jarFile) {
        if (jarFile == null || !jarFile.exists()) {
            String name = jarFile == null ? "null" : jarFile.getAbsolutePath();
            throw new IllegalArgumentException("Jar does not exist: " + name);
        }
        this.jarFile = jarFile;
    }

    public String toString() {
        return "[JarLibraryInfo: jar=" + this.jarFile.getAbsolutePath() + "; source=" + this.getSourceLocation() + "]";
    }
}

